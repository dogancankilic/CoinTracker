package com.dogancankilic.cointrackerv2.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;


import com.dogancankilic.cointrackerv2.R;

import com.dogancankilic.cointrackerv2.Repository.CoinRepository;
import com.dogancankilic.cointrackerv2.RoomDatabase.Coin;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class CoinAdapter extends RecyclerView.Adapter<CoinAdapter.ViewHolder> implements Filterable  {

    private Context context;
    private List<Coin> coins;
    private List<Coin> coinFilter;
    private CoinRepository repository;
    FilterHelper filterHelper;



    public CoinAdapter(Context context, List<Coin> coins) {
        this.context = context;
        this.coins = coins;
        this.coinFilter= new ArrayList<>(coins);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_item3, parent, false);





        return new ViewHolder(v);


    }



    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Coin coin = coinFilter.get(position);
        String imageuri = "http://api.dogancankilic.com/128x128/";
        String imageuri2="https://s2.coinmarketcap.com/static/img/coins/128x128/";




        holder.textName.setText(coin.getName());
        holder.textSymbol.setText(coin.getSymbol());
        /*holder.textYear.setText(String.format("%.15f",coin.price_usd));*/
        /*holder.textYear.setText(fmtFixed(coin.price_usd,6));*/
        holder.textPrice.setText(truncateNumber(coin.getPrice_usd()));
        double h24 = coin.getPercent_change_24h();
        holder.tx24h.setText(" "+h24);
        if (h24<0){
            holder.tx24h.setTextColor(Color.RED);

        }
        else {
            holder.tx24h.setTextColor(ContextCompat.getColor(context, R.color.green));
        }

        if (coin.getPercent_change_1h() != null){
            double h1 = coin.getPercent_change_1h();
        holder.tx1h.setText(" "+h1);
        if (h1<0){
            holder.tx1h.setTextColor(Color.RED);

        }
        else {
            holder.tx1h.setTextColor(ContextCompat.getColor(context, R.color.green));
        }

        }


        double d7 = coin.getPercent_change_7d();
        holder.tx7d.setText(" "+d7);
        if (d7<0){
            holder.tx7d.setTextColor(Color.RED);

        }
        else {
            holder.tx7d.setTextColor(ContextCompat.getColor(context, R.color.green));
        }

        if (coin.getPercent_change_1h() != null) {
            double h1s = coin.getPercent_change_1h();

            if (h1s> 0)
            {
                holder.tx1h.setText("+"+h1s );
            }
        }


        double h24s = coin.getPercent_change_24h();
        if (h24s > 0)
        {
            holder.tx24h.setText("+"+h24s);
        }

        double d7s = coin.getPercent_change_7d();

        if (d7s > 0)
        {
            holder.tx7d.setText("+"+d7s);
        }



        Picasso.get()
                .load(new StringBuilder(imageuri)
                        .append(coin.id.toLowerCase())
                        .append(".png")
                        .toString())
                .error(R.drawable.source) //.gif is not directly supported by android studio but we keep it like that. It's only a sample project.
                .into(holder.imageView);





    }




    @Override
    public int getItemCount() {
        /*return coins.size();*/
        return coinFilter.size();
    }


    public void setCoins(List<Coin> filteredCoins)
    {
        this.coinFilter=filteredCoins;
        notifyDataSetChanged();

    }

    @Override
    public Filter getFilter() {
        if(filterHelper==null)
        {
            filterHelper=new FilterHelper(coins,this,context);
        }

        return filterHelper;
    }
    public void refresh(){
        notifyDataSetChanged();
    }

    private class FilterHelper extends Filter {
        ArrayList<Coin> currentList;
        CoinAdapter adapter;
        Context c;

        public FilterHelper(List<Coin> currentList, CoinAdapter adapter, Context c) {
            this.currentList = (ArrayList<Coin>) currentList;
            this.adapter = adapter;
            this.c=c;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults=new FilterResults();

            if(constraint != null && constraint.length()>0)
            {

                constraint=constraint.toString().toLowerCase();


                ArrayList<Coin> foundFilters=new ArrayList<>();

                Coin coinFull;


                for (int i=0;i<currentList.size();i++)
                {
                    coinFull= currentList.get(i);




                    if(coinFull.id.toLowerCase().contains(constraint) )
                    {

                        foundFilters.add(coinFull);
                    }
                }


                filterResults.count=foundFilters.size();
                filterResults.values=foundFilters;

            }else
            {

                filterResults.count=currentList.size();
                filterResults.values=currentList;
            }


            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            adapter.setCoins((ArrayList<Coin>) filterResults.values);
            adapter.refresh();
        }
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textName, textSymbol, textPrice, tx24h, tx1h, tx7d, txh1s, txd1s, txd7s;
        public ImageView imageView, ig_fav;
        public CardView cv;


        public ViewHolder(final View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.coinName);
            textSymbol = itemView.findViewById(R.id.coinSymbol);
            textPrice = itemView.findViewById(R.id.priceUsd);
            imageView = itemView.findViewById(R.id.coinIcon);
            ig_fav = itemView.findViewById(R.id.ig_heart);
            tx24h = itemView.findViewById(R.id.twentyFourHour);
            tx1h = itemView.findViewById(R.id.oneHour);
            tx7d = itemView.findViewById(R.id.sevenDay);
            txh1s = itemView.findViewById(R.id.h1s);
            txd1s = itemView.findViewById(R.id.d1s);
            txd7s = itemView.findViewById(R.id.d7s);
            cv = itemView.findViewById(R.id.cw);






        }


    }

    // 2 methods for changing the usd price length.

    private static String fmtFixed(float value, int digits) {
        StringBuilder pattern = new StringBuilder("0.");
        float v = value;
        while (v >= 1) {
            digits--;
            v /= 10;
        }
        if (value < 1) {
            digits--;
        }
        while (digits > 0) {
            digits--;
            pattern.append("0");
        }
        return new DecimalFormat(pattern.toString()).format(value);
    }

    private static String truncateNumber(String input_num) {
        return input_num.substring(0, 7);
    }


}

