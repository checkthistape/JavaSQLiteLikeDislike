package com.example.sqlitelikedislike;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.CursorWindow;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.provider.ContactsContract;
import android.view.View;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnShow;
    DatabaseHelper databaseHelper = new DatabaseHelper(this);
    ArrayList<String> image_id, image_likes, image_dislikes, image_date;
    ArrayList<byte[]> image_blob;
    ArrayList<ViewPagerItem> viewPagerItemArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Default initialisation part
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShow = findViewById(R.id.buttonMain);

        /*.~~~~~~~~~~~~~.SQLite.~~~~~~~~~~~~~.*/
        // SQLite check

        //databaseHelper.checkDataBase();

        // Defining arrays, that will contain data from db
        image_id = new ArrayList<>();
        image_likes = new ArrayList<>();
        image_dislikes = new ArrayList<>();
        image_date = new ArrayList<>();
        image_blob = new ArrayList<>();

        gettingDataToArrays();
        /*.~~~~~~~~~~~~~.SQLite.~~~~~~~~~~~~~.*/

        /*.~~~~~~~~~~~~~.File checking.~~~~~~~~~~~~~.*/
        //File[] files = new File("/data/user/0/com.example.sqlitelikedislike/files/db/").listFiles();

//        System.out.println("--------------\n\n");
//        for (File i : files) {
//            System.out.println("Element " + i.toString());
//            Toast.makeText(this, "Element " + i, Toast.LENGTH_SHORT).show();
//        }
//        System.out.println("\n--------------");
        /*.~~~~~~~~~~~~~.File checking.~~~~~~~~~~~~~.*/

        // ViewPager initialisation part



        int[] images = {R.drawable.arasaka, R.drawable.rand, R.drawable.rand2, R.drawable.rand3, R.drawable.arasaka};
        int[] likes = {25, 32, 33, 55, 3};
        int[] dislikes = {3, 194, 2, 8, 4};

        viewPagerItemArrayList = new ArrayList<>();


        // Randomising images array
        Random rand = new Random();
        long seed = rand.nextLong();

        rand.setSeed(seed);
        Collections.shuffle(image_id, rand);
        rand.setSeed(seed);
        Collections.shuffle(image_likes, rand);
        rand.setSeed(seed);
        Collections.shuffle(image_dislikes, rand);
        rand.setSeed(seed);
        Collections.shuffle(image_date, rand);
        rand.setSeed(seed);
        Collections.shuffle(image_blob, rand);

//        for (int i = 0; i < images.length; i++) {
//            int randomIndexToSwap = rand.nextInt(images.length);
//            int temp = images[randomIndexToSwap];
//            images[randomIndexToSwap] = images[i];
//            images[i] = temp;
//        }



        // Sending all data to the ViewPager which is "carousel"
        for (int i = 0; i < image_blob.size(); i++) {
            Bitmap bm = BitmapFactory.decodeByteArray(image_blob.get(i), 0, image_blob.get(i).length);
            //ViewPagerItem viewPagerItem = new ViewPagerItem(images[i], likes[i], dislikes[i]);
            ViewPagerItem viewPagerItem = new ViewPagerItem(bm, Integer.parseInt(image_likes.get(i)), Integer.parseInt(image_dislikes.get(i)));
            viewPagerItemArrayList.add(viewPagerItem);
        }

        // Creating slider object and send to it all of the data
        VPAdapter vpAdapter = new VPAdapter(viewPagerItemArrayList);

        // Finding viewPager2 object in xml file
        vpAdapter.viewPager2 = findViewById(R.id.viewpager);

        // Setting properties for the viewPager
        vpAdapter.viewPager2.setAdapter(vpAdapter);
        vpAdapter.viewPager2.setClipToPadding(false);
        vpAdapter.viewPager2.setClipChildren(false);
        vpAdapter.viewPager2.setOffscreenPageLimit(2);

        vpAdapter.viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);


    }


    public void gettingDataToArrays() {

        showMessage("yoyoyo", "message is pretty important");
        Cursor cursor = databaseHelper.getAllData();

        // Trying to increase a CursorWindow size
        try {
            Field field = CursorWindow.class.getDeclaredField("sCursorWindowSize");
            field.setAccessible(true);
            field.set(null, 100 * 1024 * 1024); //the 100MB is the new size
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (cursor.getCount() == 0) {
            showMessage("Error", "Error while reading data. No data.");
            return;
        }

        while (cursor.moveToNext()) {
            image_id.add(cursor.getString(0));
            image_likes.add(cursor.getString(1));
            image_dislikes.add(cursor.getString(2));
            image_date.add(cursor.getString(3));

            image_blob.add(cursor.getBlob(4));
        }

        showMessage("Success", "Success. Data is in arrays");

        System.out.println("\nIDS:\n\n");
        for (String i : image_id) {
            System.out.println(i);
        }
        System.out.println("\nIDS:\n\n");

    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void onLike(View v) {
        VPAdapter vpadapter = new VPAdapter();

        System.out.println("\n\n  actual position is:  " + vpadapter.pos + " \n\n");
    }
}