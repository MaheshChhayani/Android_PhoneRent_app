package com.smn.rental;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "USERS")
public class User {
    @PrimaryKey
    @NonNull
    public String username;

    public String password;
}
