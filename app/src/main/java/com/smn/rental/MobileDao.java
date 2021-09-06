package com.smn.rental;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MobileDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert(Mobile e);

    @Query("SELECT COUNT(*) from MOBILES")
    public int getTotalMobile();

    @Query("select  m.*,b.username from mobiles m left join booking b on b.mobileid=m.id")
    public List<MobileUser> getAllMobile();
}
