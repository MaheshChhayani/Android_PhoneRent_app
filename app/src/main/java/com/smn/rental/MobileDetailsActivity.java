package com.smn.rental;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MobileDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Intent intent = getIntent();
        if (intent != null) {
            MobileUser mobileuser = (MobileUser) intent.getSerializableExtra("mobile");
            if (mobileuser != null) {
                TextView textMobile = findViewById(R.id.textMobileName);
                TextView textPerformance = findViewById(R.id.textPerformance);
                TextView textBattery = findViewById(R.id.textBattery);
                TextView textCamera = findViewById(R.id.textCamera);
                TextView textDisplay = findViewById(R.id.textDisplay);
                TextView textPrice = findViewById(R.id.textPrice);
                TextView textRentPrice = findViewById(R.id.textRentPrice);
                ImageView imageView = findViewById(R.id.imageView);
                TextView txtRentBy = findViewById(R.id.textRentBy);
                textMobile.setText(mobileuser.mobile.name);
                textPerformance.setText(mobileuser.mobile.performance);
                textBattery.setText(mobileuser.mobile.battery);
                textCamera.setText(mobileuser.mobile.camera);
                textDisplay.setText(mobileuser.mobile.display);
                textPrice.setText("$" + mobileuser.mobile.price);
                getSupportActionBar().setTitle(mobileuser.mobile.name);
                textRentPrice.setText("$" + String.format("%.2f", (mobileuser.mobile.price / 100 * 15)));
                imageView.setImageResource(ImageList.getImageFromName(mobileuser.mobile.imagename));

                if (TextUtils.isEmpty(mobileuser.username)) {
                    txtRentBy.setText("NA");
                } else {
                    txtRentBy.setText(mobileuser.username);
                }
            }
        }
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