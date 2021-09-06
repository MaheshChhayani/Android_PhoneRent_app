package com.smn.rental;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

public class FeedbackActivity extends AppCompatActivity {

    private SharedPreferences sp;
    private PhoneRentDatabase database;
    private FeedbackDao feedbackDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        sp = getSharedPreferences("status_preferences", MODE_PRIVATE);
        database = PhoneRentDatabase.getDatabase(getApplicationContext());
        feedbackDao = database.feedbackDao();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        LinearLayout ll=findViewById(R.id.linearLayout);
        RatingBar ratingBar=findViewById(R.id.ratingBar);
        Button btSubmit=findViewById(R.id.btSubmit);
        EditText editText=findViewById(R.id.etFeedback);
        TextView tvError=findViewById(R.id.txtError);
        TextView thankYou=findViewById(R.id.txtThankYou);
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ratingBar.getRating()==0){
                    tvError.setText("Please select your rating");
                    return;
                }
                if (TextUtils.isEmpty(editText.getText().toString())){
                    tvError.setText("Please enter feedback");
                    return;
                }
                if (sp.contains("username")) {
                    thankYou.setVisibility(View.VISIBLE);
                    ll.setVisibility(View.GONE);
                    Feedback feedback = new Feedback();
                    feedback.feedback = editText.getText().toString();
                    feedback.username =sp.getString("username","");
                    feedback.rating=String.valueOf(ratingBar.getRating());
                    feedbackDao.insert(feedback);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    },2000);
                }else {
                    tvError.setText("Please login into the application.");
                }
            }
        });
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