package com.example.literature;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class BookDetailsActivity extends AppCompatActivity {

    ImageView imgbook;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        // view
        imgbook = findViewById(R.id.item_book_img);
        ratingBar = findViewById(R.id.item_book_ratingbar);

        // get book item object

        Book item = (Book) getIntent().getExtras().getSerializable("bookObject");

        loadBookData(item);


    }

    private void loadBookData(Book item) {
        Glide.with(this).load(item.getDrawableResource()).into(imgbook);
    }
}