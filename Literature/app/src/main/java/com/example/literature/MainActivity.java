package com.example.literature;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import androidx.core.util.Pair;

public class MainActivity extends AppCompatActivity implements BookCallback {
    //using code by Aws Rh as reference

    RecyclerView rvBooks;
    BookAdapter bookAdapter;
    List<Book> mdata ;
    Button btnAddBook;
    Button btnRemove;
    Button search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        initViews();
        initmdataBooks();
        setupBookAdapter();

    }



    private void setupBookAdapter() {

        bookAdapter = new BookAdapter(mdata,this);
        rvBooks.setAdapter(bookAdapter);

    }

    private void initmdataBooks() {

        mdata = new ArrayList<>();
        mdata.add(new Book(R.drawable.book1));
        mdata.add(new Book(R.drawable.gatsby));
        mdata.add(new Book(R.drawable.gatsby2));
        mdata.add(new Book(R.drawable.nondesigner));
        mdata.add(new Book(R.drawable.thefault));
        mdata.add(new Book(R.drawable.themessy));



    }

    private void initViews() {

        btnAddBook = findViewById(R.id.btn_add);
        btnRemove = findViewById(R.id.btn_remove);
        search = findViewById(R.id.buttonSearch2);
        rvBooks = findViewById(R.id.rv_book);
        rvBooks.setLayoutManager(new LinearLayoutManager(this));
        rvBooks.setHasFixedSize(true);

        // setup custom item animator
        rvBooks.setItemAnimator(new CustomItemAnimator());

        // add book operation

        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBook();
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeBook();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
            }
        });
    }

    private void removeBook() {
        mdata.remove(1);
        bookAdapter.notifyItemRemoved(1);
    }

    private void addBook() {
        Book book = new Book(R.drawable.nondesigner);
        mdata.add(1,book);
        bookAdapter.notifyItemInserted(1);
    }

    @Override
    public void onBookItemClick(int pos,
                                ImageView imgContainer,
                                ImageView imgBook,
                                TextView title,
                                TextView authorName,
                                TextView nbpages,
                                TextView score,
                                RatingBar ratingBar) {


        // create intent and send book object to book Details activity

        Intent intent = new Intent(this,BookDetailsActivity.class);
        intent.putExtra("bookObject",mdata.get(pos));

        // shared Animation setup
        Pair<View,String> p1 = Pair.create((View)imgContainer,"containerTN");
        Pair<View,String> p2 = Pair.create((View)imgBook,"bookTN");
        Pair<View,String> p3 = Pair.create((View)title,"booktitleTN");
        Pair<View,String> p4 = Pair.create((View)authorName,"authorTN");
        Pair<View,String> p5 = Pair.create((View)nbpages,"bookpagesTN");
        Pair<View,String> p6 = Pair.create((View)score,"scoreTN");
        Pair<View,String> p7 = Pair.create((View)ratingBar,"rateTN");


        ActivityOptionsCompat optionsCompat =
                ActivityOptionsCompat.makeSceneTransitionAnimation(this,p1,p2,p3,p4,p5,p6,p7);


        // start the activity with scene transition

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            startActivity(intent,optionsCompat.toBundle());
        }
        else
            startActivity(intent);


    }
}
