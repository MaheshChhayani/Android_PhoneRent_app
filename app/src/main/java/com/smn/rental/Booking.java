package com.smn.rental;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "BOOKING")
public class Booking {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String username;

    public String bookedDate;

    @ColumnInfo(defaultValue = "NULL")
    public String returnedDate;

    public int mobileid;

    public double rent;


}
