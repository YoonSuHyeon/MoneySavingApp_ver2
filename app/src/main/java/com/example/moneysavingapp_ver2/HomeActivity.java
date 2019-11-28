package com.example.moneysavingapp_ver2;



import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private Button login,signup;
    private ArrayList<Integer> imageList;
    private static final int DP = 24; // 최대 저장 이미지수


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        login=findViewById(R.id.btn_login);
        signup=findViewById(R.id.btn_signup);
        this.initializeData();
        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setClipToPadding(false);
        float density = getResources().getDisplayMetrics().density;
        int margin = (int)(DP * density);
        //viewPager.setPadding(margin,0,margin,0);
        //viewPager.setPageMargin(margin/2);
        viewPager.setAdapter(new PagerAdapter(this,imageList));




        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginintent = new Intent(HomeActivity.this,LoginActivity.class);
                startActivity(loginintent);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signupintent = new Intent(HomeActivity.this,Sign_UpActivity.class);
                startActivity(signupintent);
            }
        });
    }

    public void initializeData(){
        imageList = new ArrayList();

        imageList.add(R.drawable.image1);
        imageList.add(R.drawable.image2);


    }
}
