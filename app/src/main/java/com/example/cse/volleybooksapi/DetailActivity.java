package com.example.cse.volleybooksapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
        ImageView imageView;
        TextView tvtitle,tvauthor,tvdes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tvtitle=findViewById(R.id.title);
        tvauthor=findViewById(R.id.author);
        tvdes=findViewById(R.id.des);
        imageView=findViewById(R.id.img);
        Picasso.with(this).load(getIntent().getStringExtra("image")).into(imageView);
        tvtitle.setText(getIntent().getStringExtra("title"));
        tvauthor.setText(getIntent().getStringExtra("author"));
        tvdes.setText(getIntent().getStringExtra("des"));
    }
}
