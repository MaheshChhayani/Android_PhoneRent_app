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

public class RegisterActivity extends AppCompatActivity {

    PhoneRentDatabase database;
    UserDao userDao;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        database = PhoneRentDatabase.getDatabase(getApplicationContext());
        userDao = database.userDao();
        sp = getSharedPreferences("status_preferences",MODE_PRIVATE);
        if(sp.contains("username")){
            Intent intent = new Intent(this, MobileActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
    }

    public void performRegistration(View view) {
        EditText etusername = findViewById(R.id.editTextUserName);
        EditText etpassword = findViewById(R.id.editTextPassword);
        EditText etconfirm = findViewById(R.id.editTextConfirm);
        String username = etusername.getText().toString().trim();
        String password = etpassword.getText().toString().trim();
        String confirm = etconfirm.getText().toString().trim();
        String error = "";
        if(username.isEmpty() || password.isEmpty() || confirm.isEmpty()){
            error = "Please enter all details.";
        }else if(password.equals(confirm)){
            User user = new User();
            user.username = username;
            user.password = password;
            userDao.insert(user);
            error = "User is Successfully Registered";
            //
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("username",username);
            editor.apply();
            //
            Intent intent = new Intent(this,MobileActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }else{
            error = "Confirm Password must match with Password";
        }

        TextView tvError = findViewById(R.id.textViewError);
        tvError.setText(error);
    }

    public void goBack(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}