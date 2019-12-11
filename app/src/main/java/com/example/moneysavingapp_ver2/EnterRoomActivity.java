package com.example.moneysavingapp_ver2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class EnterRoomActivity extends AppCompatActivity {
    private  String uid,roomname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_room);
        uid=getIntent().getStringExtra("uid");
        roomname=getIntent().getStringExtra("roomname");
        Log.d("zxcvzx",uid);
        Log.d("qwieu",roomname);
    }
}
