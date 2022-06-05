package com.example.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class adding extends AppCompatActivity {
EditText ed_title,ed_author,ed_pages;
Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding);

        ed_title = findViewById(R.id.ed_bk_title);
        ed_author = findViewById(R.id.ed_bk_author);
        ed_pages = findViewById(R.id.ed_bk_pages);

        add = findViewById(R.id.addbtn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDatabaseHelper= new MyDatabaseHelper(adding.this);
                myDatabaseHelper.addbook(ed_title.getText().toString(),
                        ed_author.getText().toString(),
                        Integer.parseInt(ed_pages.getText().toString()));
            }
        });


    }
}
