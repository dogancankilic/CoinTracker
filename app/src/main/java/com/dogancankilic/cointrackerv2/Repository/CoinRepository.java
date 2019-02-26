package com.dogancankilic.cointrackerv2.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.dogancankilic.cointrackerv2.RoomDatabase.Coin;
import com.dogancankilic.cointrackerv2.RoomDatabase.CoinDao;
import com.dogancankilic.cointrackerv2.RoomDatabase.CoinDatabase;
import com.dogancankilic.cointrackerv2.Retrofit.RestApi;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CoinRepository {

    private CoinDao coinDao;

    public static CoinDatabase coinDatabase;
    private LiveData<List<Coin>> allCoins;
    String base_url = "https://api.coinmarketcap.com/v1/ticker/";
    String base_url3 ="https://api.coingecko.com/api/v3/coins/";
    String base_url2 = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/";
    private Context context;




    public CoinRepository(Application application) {
        CoinDatabase database = CoinDatabase.getInstance(application);
        coinDao = database.coinDao();

        allCoins = coinDao.getAllCoin();

    }

    public LiveData<List<Coin>> getAllCoins() {
        return allCoins;
    }





    public void ApiCallAndPutInDB()
    {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RestApi restApi = retrofit.create(RestApi.class);

        restApi.getAllCoins().enqueue(new Callback<List<Coin>>() {
            @Override
            public void onResponse(Call<List<Coin>> call, final Response<List<Coin>> response) {

                switch (response.code()) {
                    case 200: // same as response.isSuccesfull
                        final Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {

                                /*CoinDatabase.getInstance(context).coinDao().updateCoin(response.body());*/

                                CoinDatabase.getInstance(context).coinDao().deleteAllCoins();
                                CoinDatabase.getInstance(context).coinDao().insertCoins(response.body());




                            }
                        });
                        thread.start();
                }

            }



            @Override
            public void onFailure(Call<List<Coin>> call, Throwable t) {

            }
        });



    }


}
