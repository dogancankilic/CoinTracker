package com.dogancankilic.cointrackerv2.Retrofit;

import com.dogancankilic.cointrackerv2.RoomDatabase.Coin;

import java.util.List;



import retrofit2.Call;
import retrofit2.http.GET;


public interface RestApi {



    @GET("?start=%25d&limit=10000")
    Call<List<Coin>> getAllCoins();


}
