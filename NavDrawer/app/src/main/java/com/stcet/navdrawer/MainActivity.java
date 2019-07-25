package com.stcet.navdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {
    DrawerLayout dl;
    NavigationView nv;
    TextView tv;
    ActionBarDrawerToggle toggle;
    RecyclerView recyclerView;


    String username;
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Realm.init(this);

        ActionBar toolbar = getSupportActionBar();
        toolbar.setTitle("To Do Task List");
        dl = findViewById(R.id.drawerLayout);
        nv = findViewById(R.id.navigationView);
        tv = findViewById(R.id.textView1);

        username = getIntent().getStringExtra("username");

       toggle = new ActionBarDrawerToggle(this, dl, R.string.open_menu, R.string.close_menu);

        dl.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.account:
                        Toast.makeText(MainActivity.this, "This activity", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.exit:
                        finish();
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);

    }

    public void onButtonClicked(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}