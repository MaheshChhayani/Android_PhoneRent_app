package com.smn.rental;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "MOBILES")
public class Mobile implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public String performance;
    public String battery;
    public String camera;
    public String imagename;
    public String display;
    public double price;
    public boolean status;

    @Ignore
    public Mobile() {
    }

    public Mobile(String name, String performance, String battery, String camera, String imagename, String display, double price) {
        this.name = name;
        this.performance = performance;
        this.battery = battery;
        this.camera = camera;
        this.imagename = imagename;
        this.display = display;
        this.price = price;
        this.status = false;
    }


}
