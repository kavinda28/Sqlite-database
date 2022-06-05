package com.example.sqlitedatabase;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomeAdapter extends RecyclerView.Adapter<CustomeAdapter.MyViewHolder> {
    private Context context;
    private Activity activity;
    private ArrayList book_id, book_title, book_author, book_pages;
Animation translate_animation;
    public CustomeAdapter(Activity activity,Context context, ArrayList book_id, ArrayList book_title, ArrayList book_author, ArrayList book_pages) {
        this.activity =activity;
        this.context = context;
        this.book_id = book_id;
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_pages = book_pages;

    }

    @NonNull
    @Override
    public CustomeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.mycart_desing, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomeAdapter.MyViewHolder holder, int position) {
        holder.id.setText(String.valueOf(book_id.get(position)));
        holder.title.setText(String.valueOf(book_title.get(position)));
        holder.author.setText(String.valueOf(book_author.get(position)));
        holder.pages.setText(String.valueOf(book_pages.get(position)));
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context,update.class);
                intent.putExtra("id",String.valueOf(book_id.get(position)));
                intent.putExtra("title",String.valueOf(book_title.get(position)));
                intent.putExtra("author",String.valueOf(book_author.get(position)));
                intent.putExtra("pages",String.valueOf(book_pages.get(position)));
               activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return book_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id, title, author, pages;
        ConstraintLayout constraintLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.b_id);
            title = itemView.findViewById(R.id.b_title);
            author = itemView.findViewById(R.id.b_author);
            pages = itemView.findViewById(R.id.b_pages);
            constraintLayout = itemView.findViewById(R.id.constrainlayout);

            //Animation Recyclerview
            translate_animation = AnimationUtils.loadAnimation(context,R.anim.recycle_animation);
            constraintLayout.setAnimation(translate_animation);

        }
    }
}
