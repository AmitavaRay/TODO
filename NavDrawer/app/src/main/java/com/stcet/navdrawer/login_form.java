package com.stcet.navdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.stcet.navdrawer.MainActivity;

import java.util.Set;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class login_form extends AppCompatActivity {

    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        getSupportActionBar().setTitle("To Do Task List");
//        Realm.init(this);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
    }

    public void Signup(View view) {
        Intent intent = new Intent(this, login_form.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        try {
//            Set realmSet=mRealm.copy
            User user = realm.createObject(User.class, username.getText().toString());
            user.setPassword(password.getText().toString());


            realm.commitTransaction();
            Toast.makeText(this, "Successful ", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            realm.cancelTransaction();
            Toast.makeText(this, "Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
        realm.close();
    }
        public void login(View view){

            if (username.getText().toString().equals("") || password.getText().toString().equals(""))
                Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_LONG).show();
            else {
            /*RealmConfiguration config = new RealmConfiguration.Builder()
                    .deleteRealmIfMigrationNeeded()
                    .build();*/
//            Realm.init(this);
                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                User user = realm.where(User.class).equalTo("username", username.getText().toString()).findFirst();
                if (user == null) {
                    Toast.makeText(this, "User does not exist", Toast.LENGTH_LONG).show();
                } else {
                    String userpass = user.getPassword();
                    if (password.getText().toString().equals(userpass)) {
                        realm.close();
                        Toast.makeText(this, "Welcome " + username.getText().toString(), Toast.LENGTH_LONG).show();
                        Intent intent1 = new Intent(this, MainActivity.class);
                        intent1.putExtra("username", username.getText().toString());
                        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent1);
                    } else {
                        Toast.makeText(this, "Wrong Credentials", Toast.LENGTH_LONG).show();
                        username.setText("");
                        password.setText("");
                    }
                }
            }
        }
    }



