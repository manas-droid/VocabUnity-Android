package com.example.vocabunity_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView view = findViewById(R.id.nav_view);
        view.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar ,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
            view.setCheckedItem(R.id.home);
        }
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.home:
                getSupportFragmentManager()
                .beginTransaction().replace(R.id.fragment_container,
                 new HomeFragment()).commit();
                break;

            case R.id.user_profile:
                getSupportFragmentManager()
                        .beginTransaction().replace(R.id.fragment_container,
                        new UserProfileFragment()).commit();
                break;

            case R.id.add_vocab:
                getSupportFragmentManager()
                        .beginTransaction().replace(R.id.fragment_container,
                        new AddToPostFragment()).commit();
                break;

            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}