package com.stcet.navdrawer;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import io.realm.Realm;

public class SecondActivity extends AppCompatActivity {
    public static final String TAG = "SecondActivity";
    private Context context;
    private EditText name;
    private EditText Due_by;
    private EditText Holder_color;

    DrawerLayout dl;
    NavigationView nv;
    TextView tv;
    ActionBarDrawerToggle toggle;

    String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        dl = findViewById(R.id.drawerLayout);
        nv = findViewById(R.id.navigationView);
        tv = findViewById(R.id.textView1);

        toggle = new ActionBarDrawerToggle(this, dl, R.string.open_menu, R.string.close_menu);

        username = getIntent().getStringExtra("username");

        dl.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setTitle(" Create To-Do Task ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.account:
                        Toast.makeText(SecondActivity.this, "This activity", Toast.LENGTH_LONG).show();
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





        context = this;
        name = findViewById(R.id.main_name_et);
        Due_by = findViewById(R.id.main_dept_et);
        Holder_color = findViewById(R.id.main_roll_et);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(toggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);

    }
    public void buttona(View view) {

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        try {

            User user = realm.where(User.class).equalTo("username", username).findFirst();
            user.setHolder_color(Holder_color.getText().toString());
            user.setTempname(name.getText().toString());
            user.setTempdue(Due_by.getText().toString());

            Intent intent = new Intent(this, DisplayActivity.class);
            startActivity(intent);



            realm.commitTransaction();
            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();


        } catch (Exception e) {
            realm.cancelTransaction();
            Toast.makeText(context, "Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();

        }

        realm.close();


    }
    public void buttonb(View view) {

        finish();

    }

}
