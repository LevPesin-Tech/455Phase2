package com.example.literature;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdditionClass extends AppCompatActivity{
    Button send;
    Button list;
    Button search;
    EditText editText;
    String book;

    @Override
    protected void  onCreate(Bundle  savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propose);

        send = findViewById(R.id.buttonSend);
        list = findViewById(R.id.buttonList);
        search = findViewById(R.id.buttonSearch);
        editText = findViewById(R.id.editTextBook);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdditionClass.this,
                        "Thank you!", Toast.LENGTH_LONG).show();
                book = editText.getText().toString();
                editText.getText().clear();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.item_book);
            }
        });
    }
}
