package com.smn.rental;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Date;

public class MobileActivity extends AppCompatActivity {

    private PhoneRentDatabase database;
    private MobileDao mobileDao;
    private ArrayList<MobileUser> mobiles;
    private RecyclerView recyclerView;
    private MobileAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private SharedPreferences sp;
    private TextView txtUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile);
        database = PhoneRentDatabase.getDatabase(getApplicationContext());
        mobileDao = database.mobileDao();
        sp = getSharedPreferences("status_preferences", MODE_PRIVATE);

        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.nav_home:

                        break;
                    case R.id.nav_feedback:
                        Intent intent = new Intent(MobileActivity.this, FeedbackActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_about_us:
                        Intent intent2 = new Intent(MobileActivity.this, AboutUsActivity.class);
                        startActivity(intent2);
                        break;
                }
                drawerLayout.closeDrawers();
                return false;
            }
        });

        View header = navigationView.getHeaderView(0);
        txtUserName = header.findViewById(R.id.txtUsername);
        mobiles = (ArrayList<MobileUser>) mobileDao.getAllMobile();
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MobileAdapter(this, mobiles, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btRent) {
                    if (sp.contains("username")) {
                        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(MobileActivity.this);
                        materialAlertDialogBuilder.setTitle("Rent");
                        materialAlertDialogBuilder.setMessage("Are you sure, you want to rent this mobile?");
                        materialAlertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        materialAlertDialogBuilder.setPositiveButton("Rent Now", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Mobile mobile = (Mobile) v.getTag();
                                Booking booking = new Booking();
                                booking.username = sp.getString("username", "");
                                booking.mobileid = mobile.id;
                                booking.rent = mobile.price / 100 * 15;
                                booking.bookedDate = new Date().toString();
                                database.bookingDao().insert(booking);
                                mobiles.clear();
                                mobiles.addAll(mobileDao.getAllMobile());
                                adapter.notifyDataSetChanged();
                            }
                        });
                        materialAlertDialogBuilder.show();
                    } else {
                        Intent intent = new Intent(MobileActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }

                } else if (v.getId() == R.id.buttonViewDetails) {
                    Button btn = (Button) v;
                    MobileUser mobile = (MobileUser) btn.getTag();
                    Intent intent = new Intent(MobileActivity.this, MobileDetailsActivity.class);
                    intent.putExtra("mobile", mobile);
                    startActivity(intent);
                }
            }
        });
        recyclerView.setAdapter(adapter);

        if (sp.contains("username")) {
            txtUserName.setText("Hello " + sp.getString("username", ""));
        } else {
            txtUserName.setText("");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mobile, menu);
        menu.getItem(0).setVisible(sp.contains("username"));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        } else if (item.getItemId() == R.id.menu_logout) {
            MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(this);
            materialAlertDialogBuilder.setTitle("Logout");
            materialAlertDialogBuilder.setMessage("Are you sure, you want to logout?");
            materialAlertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            materialAlertDialogBuilder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    SharedPreferences.Editor editor = sp.edit();
                    editor.remove("username");
                    editor.apply();
                    Intent intent = new Intent(MobileActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
            });
            materialAlertDialogBuilder.show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }
}