package com.manan.mchat.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.manan.mchat.R;

import java.util.ArrayList;

import Adapter.RecyclerViewAdapter;

public class chat_list extends AppCompatActivity {
    private static final String TAG="chat_list";
    //vars
    private ArrayList<String> mNames=new ArrayList<>();
    private ArrayList<String>mmsg=new ArrayList<>();
    private ArrayList<String>mImageUrls=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);
        Log.d(TAG, "onCreate: started.");
        initImageBitmaps();
    }
    private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImageUrls.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        mNames.add("Amit Kumar");
        mmsg.add("This is a Dummy message");

        mImageUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        mNames.add("Rishabh Mahajan");
        mmsg.add("This is a Dummy message");

        mImageUrls.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mNames.add("Shubham Sharma");
        mmsg.add("This is a Dummy message");

        mImageUrls.add("https://i.redd.it/j6myfqglup501.jpg");
        mNames.add("Hemant Sir");
        mmsg.add("This is a Dummy message");


        mImageUrls.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        mNames.add("Prerna Suneja");
        mmsg.add("This is a Dummy message");

        mImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg");
        mNames.add("Nisha");
        mmsg.add("This is a Dummy message");


        mImageUrls.add("https://i.redd.it/glin0nwndo501.jpg");
        mNames.add("Yugant Kachroo");
        mmsg.add("This is a Dummy message");

        mImageUrls.add("https://i.redd.it/obx4zydshg601.jpg");
        mNames.add("Ankit Mittal");
        mmsg.add("This is a Dummy message");

        mImageUrls.add("https://i.imgur.com/ZcLLrkY.jpg");
        mNames.add("Mohit Yadav");
        mmsg.add("This is a Dummy message");

        initRecyclerView();
    }
    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames,mmsg,mImageUrls);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}

