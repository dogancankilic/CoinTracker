package com.dogancankilic.cointrackerv2.RoomDatabase;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "coin_table")
public class Coin   {
    @PrimaryKey(autoGenerate = true)
    @android.support.annotation.NonNull
    public int ids;
    @ColumnInfo(name = "id")
    public String id;
    public String name;
    public String symbol;
    public String rank;
    public String price_usd;
    private Double percent_change_1h;
    private Double percent_change_24h;
    private Double percent_change_7d;
    @ColumnInfo(name = "24h_volume_usd")
    @SerializedName("24h_volume_usd")
    public String t24h_volume_usd;
    public String market_cap_usd;


    @Ignore
    public Coin(String id, String name, String symbol, String rank, String price_usd, Double percent_change_1h, Double percent_change_24h, Double percent_change_7d) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.rank = rank;
        this.price_usd = price_usd;
        this.percent_change_1h = percent_change_1h;
        this.percent_change_24h = percent_change_24h;
        this.percent_change_7d = percent_change_7d;
    }

    public Coin() {
    }

    public String getT24h_volume_usd() {
        return t24h_volume_usd;
    }

    public void setT24h_volume_usd(String t24h_volume_usd) {
        this.t24h_volume_usd = t24h_volume_usd;
    }

    public String getMarket_cap_usd() {
        return market_cap_usd;
    }

    public void setMarket_cap_usd(String market_cap_usd) {
        this.market_cap_usd = market_cap_usd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getPrice_usd() {
        return price_usd;
    }

    public void setPrice_usd(String price_usd) {
        this.price_usd = price_usd;
    }

    public Double getPercent_change_1h() {
        return percent_change_1h;
    }

    public void setPercent_change_1h(Double percent_change_1h) {
        this.percent_change_1h = percent_change_1h;
    }

    public Double getPercent_change_24h() {
        return percent_change_24h;
    }

    public void setPercent_change_24h(Double percent_change_24h) {
        this.percent_change_24h = percent_change_24h;
    }

    public Double getPercent_change_7d() {
        return percent_change_7d;
    }

    public void setPercent_change_7d(Double percent_change_7d) {
        this.percent_change_7d = percent_change_7d;
    }

}
