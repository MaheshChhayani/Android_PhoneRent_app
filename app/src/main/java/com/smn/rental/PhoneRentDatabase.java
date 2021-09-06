package com.smn.rental;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Mobile.class,User.class,Booking.class,Feedback.class}, version = 1, exportSchema = false)
public abstract class PhoneRentDatabase extends RoomDatabase {
    public abstract MobileDao mobileDao();
    public abstract UserDao userDao();
    public abstract BookingDao bookingDao();
    public abstract FeedbackDao feedbackDao();

    private static volatile PhoneRentDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static PhoneRentDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PhoneRentDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PhoneRentDatabase.class, "mobile_db")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
