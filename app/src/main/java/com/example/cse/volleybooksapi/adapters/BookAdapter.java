package com.example.cse.volleybooksapi.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.cse.volleybooksapi.DetailActivity;
import com.example.cse.volleybooksapi.MainActivity;
import com.example.cse.volleybooksapi.R;
import com.example.cse.volleybooksapi.model.BookModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.SubClass> {
    Context context;
    ArrayList<BookModel> arrayList;

    public BookAdapter(MainActivity mainActivity, ArrayList<BookModel> arrayList) {
        this.context=mainActivity;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public BookAdapter.SubClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new SubClass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapter.SubClass holder, int position) {
        Picasso.with(context).load(arrayList.get(position).getImageLink()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class SubClass extends RecyclerView.ViewHolder {
        ImageView imageView;
        public SubClass(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imgView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    if (position!=RecyclerView.NO_POSITION){
                        Intent intent=new Intent(context,DetailActivity.class);
                        intent.putExtra("title",arrayList.get(position).getBooktitle());
                        intent.putExtra("author",arrayList.get(position).getAuthor());
                        intent.putExtra("desc",arrayList.get(position).getDesc());
                        intent.putExtra("image",arrayList.get(position).getImageLink());
                                context.startActivity(intent);
                    }
                }
            });
        }
    }
}
