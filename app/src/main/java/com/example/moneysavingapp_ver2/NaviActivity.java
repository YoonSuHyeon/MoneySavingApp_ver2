package com.example.moneysavingapp_ver2;

import android.content.Intent;
import android.os.Bundle;

import com.example.moneysavingapp_ver2.ui.gallery.GalleryFragment;
import com.example.moneysavingapp_ver2.ui.home.HomeFragment;
import com.example.moneysavingapp_ver2.ui.slideshow.SlideshowFragment;
import com.example.moneysavingapp_ver2.ui.tools.ToolsFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.internal.NavigationMenuItemView;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;

public class NaviActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private  String uid,nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navi);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent =getIntent();
        uid =intent.getStringExtra("uid");
        nickname=intent.getStringExtra("nickname");

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if(destination.getId()==R.id.nav_gallery ||destination.getId()==R.id.nav_slideshow ||destination.getId()==R.id.nav_tools ) {
                    assert arguments != null;
                    arguments.putString("uid",uid);
                    arguments.putString("nickname",nickname);
                }

            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navi, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


}
