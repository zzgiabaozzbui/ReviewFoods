package com.edu.baogia.introducefood.view.activity;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.edu.baogia.introducefood.R;

public class SearchMainActivity extends AppCompatActivity {
    EditText edtSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_main);
        edtSearch = findViewById(R.id.edtSearch);
        edtSearch.requestFocus();

    }
}