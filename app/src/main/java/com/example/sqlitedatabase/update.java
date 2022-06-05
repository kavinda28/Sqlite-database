package com.example.sqlitedatabase;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class update extends AppCompatActivity {
    EditText ed_title, ed_author, ed_pages;
    Button updatebtn, deletebtn;
    String title, author, pages, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        ed_title = findViewById(R.id.up_bk_title);
        ed_author = findViewById(R.id.up_bk_author);
        ed_pages = findViewById(R.id.up_bk_pages);

        updatebtn = findViewById(R.id.updates);
        deletebtn = findViewById(R.id.delete);

        get_intent_and_update_edt_text();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(title);


        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(update.this);
                title = ed_title.getText().toString().trim();
                author = ed_author.getText().toString().trim();
                pages = ed_pages.getText().toString().trim();

                myDatabaseHelper.update_data(id, title, author, pages);

            }
        });

        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
         ConfirmDialog();
            }
        });
    }

    void get_intent_and_update_edt_text() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("author") && getIntent().hasExtra("pages")) {
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            pages = getIntent().getStringExtra("pages");
            ed_title.setText(title);
            ed_author.setText(author);
            ed_pages.setText(pages);


        } else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }


    void ConfirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete "+ title +"?");
        builder.setMessage("Are you sure you went to delete "+title+ " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDatabaseHelper =new MyDatabaseHelper(update.this);
                myDatabaseHelper.delete_one_row(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(update.this, "Cancel!", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }
}
