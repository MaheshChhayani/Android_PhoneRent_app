package com.smn.rental;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    PhoneRentDatabase database;
    MobileDao mobileDao;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        database = PhoneRentDatabase.getDatabase(this);
        mobileDao = database.mobileDao();
        sp = getSharedPreferences("status_preferences",MODE_PRIVATE);
        //wait for two seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                populateMobileDetails();
            }
        },2000);

    }
    //adding data into the database.
    public void populateMobileDetails() {
        if (mobileDao.getTotalMobile() == 0) {
            //String name, String performance, String battery, String camera, String imagename, String display, double price
            Mobile mobiles[] = new Mobile[]{
                    new Mobile("Realme 8 Pro", "Octa Core 2.3 GHz Snapdragon 720G 6 GB Ram ", "4500 mAh Super Dart Charging USB Type-C Port", "108 + 8 + 2 + 2 MP Quad LED Flash 16MP Front Camera", "mobile1.jpg", "6.4 inches (16.26 cm) 411 PPI, Super Amoled 60 Hz Refresh Rate", 375.8),
                    new Mobile("One Plus 9 Pro", "Qualcomm Snapdragon 888 Octa core (2.84 Ghz) 64 bit 5nm Adreno 660 8GB Ram", "4500 mAh Warp Charging USB Type-C Port", "48 + 50 + 8 + 2 MP Quad Duel LED Flash 16 MP Front Camera", "mobile2.jpg", "6.7 inches (17.02 cm) 526 PPI, Fluid AMOLED 120 Hz Refresh Rate", 1299.98),
                    new Mobile("OPPO F19 PRO", "Octa core (2.2 GHz) MediaTek Helio P95 8 GB RAM", "4310 mAh Flash Charging 4.0 USB Type-C Port", "48 + 8 + 2 + 2 MP Quad, LED Flash 16 MP Front Camera", "mobile3.jpg", "6.43 inches (16.33 cm) 409 PPI, Super AMOLED 60 Hz Refresh Rate", 429.8),
                    new Mobile("VIVO X60 PRO", "Octa core (3.2 GHz) Snapdragon 870 12 GB RAM", "4200 mAh Flash Charging USB Type-C Port", "48 MP + 13 MP + 13 MP + 13 MP Quad, LED Flash, 32 MP Front Camera", "mobile4.jpg", "6.56 inches (16.66 cm) 398 PPI, AMOLED 120 Hz Refresh Rate", 999.8),
                    new Mobile("Samsung Galaxy A52", "Octa core (2.3 GHz) Snapdragon 720G 6 GB RAM", "4500 mAh Fast Charging USB Type-C Port", "64 + 12 + 5 + 5 MP Quad, LED Flash 32 MP CAmera", "mobile5.jpg", "6.5 inches (16.51 cm) 405 PPI, Super AMOLED, 90 Hz Referesh Rate", 529.8),
                    new Mobile("OPPO F19 PRO PLUS", "Octa core (2.4 GHz) MediaTek Dimensity 8GB RAM", "4310 mAh Flash Charging USB Type-C Port", "48 + 8 + 2 + 2 MP Quad, LED Flash 16 MP Front Camera", "mobile6.jpg", "6.43 inches (16.33 cm) 409 PPI, Super AMOLED 60 Hz Refresh Rate", 475.8),
                    new Mobile("Apple IPhone 12", "Hexa Core 3.1 GHz, Apple A14 Bionic, 4 GB RAM", "6.1 inches (15.49 cm) 457 PPI, OLED", "12 MP + 12 MP Dual, Dual LED Flash, 12 MP Front Camera", "mobile7.jpg", "2815 mAh, Fast Charging Lightning Port", 1555.8)
            };
            for (Mobile mobile : mobiles) {
                mobileDao.insert(mobile);
            }
        }

        //checking use in sharedpreferences
        sp = getSharedPreferences("status_preferences",MODE_PRIVATE);
        if(sp.contains("username")){

            Intent intent = new Intent(this,MobileActivity.class);
            //remove all activity from backstack.
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }
}