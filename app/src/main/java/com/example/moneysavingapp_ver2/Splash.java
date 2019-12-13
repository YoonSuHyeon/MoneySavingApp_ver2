package com.example.moneysavingapp_ver2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new splashHandler(),3000);
    }


    private class splashHandler implements Runnable{
        public void run(){
            startActivity(new Intent(getApplication(),HomeActivity.class));
            Splash.this.finish();
        }
    }

    @Override
    public void onBackPressed() {

    }
}
