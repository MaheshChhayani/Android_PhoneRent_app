package com.smn.rental;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert(User u);

    @Query("SELECT * FROM USERS WHERE username=:userName")
    public User findUser(String userName);


}
