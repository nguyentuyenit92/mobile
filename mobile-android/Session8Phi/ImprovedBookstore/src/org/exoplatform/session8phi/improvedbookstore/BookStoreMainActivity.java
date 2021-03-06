package org.exoplatform.session8phi.improvedbookstore;

import org.exoplatform.session8phi.improvedbookstore.BookStoreDBHelper.Book;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class BookStoreMainActivity extends ListActivity {

	private BookStoreDBHelper db;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_book_store_main);
        db = new BookStoreDBHelper(this);
        
        populateList();
        
    }

    @Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		populateList();
	}
    
    private void populateList() {
    	Cursor c = db.getAllBooks();
        String[] from = {
        		Book.BOOK_TITLE,
        		Book.BOOK_AUTHOR,
        		Book.BOOK_ID,
        		Book.BOOK_PRICE
        };
        int[] to = {
        		R.id.text_book_item_title,
        		R.id.text_book_item_author,
        		R.id.text_book_item_id,
        		R.id.text_book_item_price
        };
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
        		this,
        		R.layout.list_book_item,
        		c,
        		from,
        		to,
        		0);
        setListAdapter(adapter);
    }

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
    	String bookId = ((TextView)v.findViewById(R.id.text_book_item_id)).getText().toString();
		Intent showBookActivity = new Intent(getBaseContext(), ViewBookDetailsActivity.class);
		showBookActivity.putExtra(Book.BOOK_ID, bookId);
    	startActivity(showBookActivity);
	}




	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_book_store_main, menu);
        return true;
    }


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
	    switch (item.getItemId()) {
	        case R.id.menu_add:
	            Intent addIntent = new Intent(this, AddBookActivity.class);
	            startActivity(addIntent);
	            return true;
	        case R.id.menu_search:
	            Intent searchIntent = new Intent(this, SearchBookActivity.class);
	            startActivity(searchIntent);
	            return true;
	        case R.id.menu_exit:
	        	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	        	builder.setMessage(getString(R.string.menu_exit_app)+"?")
	        	       .setCancelable(true)
	        	       .setPositiveButton(getString(R.string.word_yes), new DialogInterface.OnClickListener() {
	        	           public void onClick(DialogInterface dialog, int id) {
	        	                finish();
	        	           }
	        	       })
	        	       .setNegativeButton(getString(R.string.word_no), new DialogInterface.OnClickListener() {
	        	           public void onClick(DialogInterface dialog, int id) {
	        	                dialog.cancel();
	        	           }
	        	       });
	        	AlertDialog alert = builder.create();
	        	alert.show();
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
    
    
}
