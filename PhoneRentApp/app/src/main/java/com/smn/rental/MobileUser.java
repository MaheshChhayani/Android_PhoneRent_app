package com.smn.rental;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

import java.io.Serializable;

public class MobileUser implements Serializable {
    @Embedded
    Mobile mobile;

    @ColumnInfo(name = "username")
    String username;
}
