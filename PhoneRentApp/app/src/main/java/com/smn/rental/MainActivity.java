package com.smn.rental;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    public void viewMobiles(View view) {
        Intent intent = new Intent(this, MobileActivity.class);
        startActivity(intent);
       // finish();
    }




    public void viewLogin(View view) {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
       // finish();
    }

    public void viewRegister(View view) {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
        //finish();
    }

}