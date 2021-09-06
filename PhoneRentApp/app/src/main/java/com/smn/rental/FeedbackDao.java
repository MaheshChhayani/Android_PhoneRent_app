package com.smn.rental;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

@Dao
public interface FeedbackDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert(Feedback u);

}
