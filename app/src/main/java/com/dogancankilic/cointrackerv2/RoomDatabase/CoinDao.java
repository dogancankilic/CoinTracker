package com.dogancankilic.cointrackerv2.RoomDatabase;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface CoinDao {
    //This is the livedata where we observe the changes on our database.
    @Query("SELECT * FROM coin_table")
    LiveData<List<Coin>> getAllCoin();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     void insertCoins(List<Coin> coins);

    @Query("DELETE FROM coin_table")
     void deleteAllCoins();











}
