package com.example.sqlitelikedislike;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

import android.graphics.Color;
public class VPAdapter extends RecyclerView.Adapter<VPAdapter.ViewHolder> {
    ArrayList<ViewPagerItem> viewPagerItemArrayList;
    ViewPager2 viewPager2;
    int pos;

    public VPAdapter(){ }

    public VPAdapter(ArrayList<ViewPagerItem> viewPagerItemArrayList){
        this.viewPagerItemArrayList=viewPagerItemArrayList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewpager_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){

        pos = holder.getAdapterPosition();

        // Creates infinity swiping
        if(position==viewPagerItemArrayList.size()-1){
            viewPager2.post(runnable);
        }

        ViewPagerItem viewPagerItem = viewPagerItemArrayList.get(position);

        System.out.println("Position is: " + position);
        System.out.println(viewPagerItem);

        //holder.imageView.setImageResource(viewPagerItem.imageId);
        //holder.imageView.setImageResource(viewPagerItem.image);
        holder.imageView.setImageBitmap(viewPagerItem.image);

        holder.buttonLike.setText("Like " + viewPagerItem.likes+"");
        holder.buttonDis.setText("Dislike " + viewPagerItem.dislikes+"");

    }

    @Override
    public int getItemCount(){
        return viewPagerItemArrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView; // Image on an ever slide
        TextView tcHeading, tvDesc; // Title on the bottom and description for it

        Button buttonLike, buttonDis;

         public ViewHolder(@NonNull View itemView){
             super(itemView);

             imageView = itemView.findViewById(R.id.ivimage);
             buttonLike = (Button)itemView.findViewById(R.id.buttonLike);
             buttonDis = (Button)itemView.findViewById(R.id.buttonDis);
         }
    }

    // Method for infinity swiping
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            viewPagerItemArrayList.addAll(viewPagerItemArrayList);
            notifyDataSetChanged();
        }
    };
}


