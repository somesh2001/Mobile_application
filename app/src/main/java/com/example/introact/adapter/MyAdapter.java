package com.example.introact.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.introact.R;
import com.example.introact.model.Fruits;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
    private Context context;
    private ArrayList<Fruits> arrayList;
    private static MyAdapter.OnItemClickListener listner;

    public interface OnItemClickListener{
        void onItemClick(int position);

    }
    public void setOnItemClickListener(MyAdapter.OnItemClickListener listner)
    {
        MyAdapter.listner = (MyAdapter.OnItemClickListener) listner;
    }
    public MyAdapter(Context context, ArrayList<Fruits> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }
    @NonNull
    @Override
    public MyAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mycardview,parent,false);
        return new MyAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        Fruits mDrinks = arrayList.get(position);
        Picasso.get().load(mDrinks.getImage()).centerCrop().fit().noFade().into(holder.imageView);
        holder.textView.setText(mDrinks.getName());
        holder.field.setText(mDrinks.getCategory());

    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private CircleImageView imageView;
        private AppCompatTextView textView,field;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.fruitname);
            field = itemView.findViewById(R.id.category);
            imageView = itemView.findViewById(R.id.fruitimage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        listner.onItemClick(position);
                    }

                }
            });
        }
    }
}

