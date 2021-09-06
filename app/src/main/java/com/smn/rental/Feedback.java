package com.smn.rental;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Feedback")
public class Feedback {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String username;

    public String feedback;

    public String rating;


}
