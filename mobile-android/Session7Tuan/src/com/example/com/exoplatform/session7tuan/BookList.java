package com.example.com.exoplatform.session7tuan;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class BookList extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_book_list, menu);
        return true;
    }
}
