package com.smn.rental;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    PhoneRentDatabase database;
    UserDao userDao;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        database = PhoneRentDatabase.getDatabase(getApplicationContext());
        userDao = database.userDao();
        sp = getSharedPreferences("status_preferences",MODE_PRIVATE);
        //back arrow visible
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void validateLogin(View view) {
        EditText etusername = findViewById(R.id.editTextUserName);
        EditText etpassword = findViewById(R.id.editTextPassword);
        String username = etusername.getText().toString().trim();
        String password = etpassword.getText().toString().trim();
        String error = "";
        if(username.isEmpty() || password.isEmpty()){
            error = "Please enter all details";
        }else {
            User user = userDao.findUser(username);
            if(user!=null){
                if(user.password.equals(password)){
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("username",username);
                    editor.apply();
                    Intent intent = new Intent(this,MobileActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }else{
                    error = "Username / Password combination does not exists";
                }
            }else{
                error = "User Name does not exists";
            }
        }
        TextView tvError = findViewById(R.id.textViewError);
        tvError.setText(error);
    }

    public void validateRegister(View view) {
        finish();
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }
    //
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //click on the back arrow
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}