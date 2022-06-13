package com.edu.baogia.introducefood.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.edu.baogia.introducefood.R;

public class SearchMainActivity extends AppCompatActivity {
    EditText edtSearch;
    TextView txtSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_main);
        edtSearch = findViewById(R.id.edtSearch);
        txtSearch = findViewById(R.id.txtSearch);
        edtSearch.requestFocus();

        txtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchMainActivity.this,DanhMucMonAnView.class);
                intent.putExtra("timkiem",edtSearch.getText().toString());
                startActivity(intent);
            }
        });
    }
}