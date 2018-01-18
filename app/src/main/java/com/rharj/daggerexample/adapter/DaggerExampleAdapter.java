package com.rharj.daggerexample.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rharj.daggerexample.R;
import com.rharj.daggerexample.activity.MainActivity;
import com.rharj.daggerexample.model.DaggerExampleModel;
import com.rharj.daggerexample.model.DaggerResult;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rharj on 1/16/2018.
 */

public class DaggerExampleAdapter extends RecyclerView.Adapter<DaggerExampleAdapter.DaggerViewHolder> {

    private final Picasso picasso;
    private List<String> resultList = new ArrayList<>();


    public DaggerExampleAdapter(MainActivity mainActivity, Picasso picasso) {
        this.picasso = picasso;
    }

    @Override
    public DaggerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_random_user,
                parent, false);
        return new DaggerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DaggerViewHolder holder, int position) {
        String daggerResult = resultList.get(position);
        holder.tDogName.setText(daggerResult);
        /*picasso.with(holder.imageView.getContext())
                .load(result.getPicture().getLarge())
                .into(holder.imageView);*/
    }

    public void setItems(List<String> results) {
        resultList = results;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public class DaggerViewHolder extends RecyclerView.ViewHolder {
        public TextView tDogName;

        public DaggerViewHolder(View itemView) {
            super(itemView);
            tDogName = itemView.findViewById(R.id.tDogName);
        }
    }
}
