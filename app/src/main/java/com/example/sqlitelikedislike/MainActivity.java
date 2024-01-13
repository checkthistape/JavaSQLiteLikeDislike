package com.example.sqlitelikedislike;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;
public class MainActivity extends AppCompatActivity {

    ArrayList<ViewPagerItem> viewPagerItemArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ViewPager initialisation part

        int[] images = {R.drawable.arasaka, R.drawable.rand, R.drawable.rand2, R.drawable.rand3, R.drawable.arasaka};
//        String[] heading = {"Arasaka1", "Arasaka2", "Arasaka3", "Arasaka4", "Arasaka5"};
//        String[] desc = {getString(R.string.a_desc), getString(R.string.b_desc), getString(R.string.c_desc), getString(R.string.d_desc), getString(R.string.e_desc)};
        int[] likes = {25, 32, 33, 55, 3};
        int[] dislikes = {3, 194, 2, 8, 4};
        viewPagerItemArrayList = new ArrayList<>();


        // Randomising images array
        Random rand = new Random();

        for (int i = 0; i < images.length; i++) {
            int randomIndexToSwap = rand.nextInt(images.length);
            int temp = images[randomIndexToSwap];
            images[randomIndexToSwap] = images[i];
            images[i] = temp;
        }


        for(int i = 0; i<images.length; i++){
            ViewPagerItem viewPagerItem = new ViewPagerItem(images[i], likes[i], dislikes[i]);
            viewPagerItemArrayList.add(viewPagerItem);
        }

        VPAdapter vpAdapter = new VPAdapter(viewPagerItemArrayList);

        vpAdapter.viewPager2 = findViewById(R.id.viewpager);

        vpAdapter.viewPager2.setAdapter(vpAdapter);
        vpAdapter.viewPager2.setClipToPadding(false);
        vpAdapter.viewPager2.setClipChildren(false);
        vpAdapter.viewPager2.setOffscreenPageLimit(2);

        vpAdapter.viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
    }

    public void onLike(View v){
        VPAdapter vpadapter = new VPAdapter();

        System.out.println("\n\n  actual position is:  "+ vpadapter.pos + " \n\n");
    }
}