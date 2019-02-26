package com.dogancankilic.cointrackerv2.RoomDatabase;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Coin.class}, version = 6)
public abstract class CoinDatabase extends RoomDatabase {

    private static CoinDatabase instance;

    public abstract CoinDao coinDao();

    

    //singleton
    public static synchronized CoinDatabase getInstance (Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    CoinDatabase.class, "coin_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }


    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopDbAsync(instance).execute();
        }
    };

    private static class PopDbAsync extends AsyncTask<Void,Void,Void> {
        private CoinDao coinDao;



        private PopDbAsync(CoinDatabase db) {
            coinDao = db.coinDao();

        }


        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
