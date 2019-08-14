package com.ernestovaldez.androidapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<Numero> mDataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        public View view;
        public MyViewHolder(View v) {
            super(v);
            view = v;
            textView = view.findViewById(R.id.txtNumber);
        }
    }


    public MyAdapter(ArrayList<Numero> myDataset) {
        mDataset = myDataset;
    }


    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cellcomponent, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        String text = "Numero: " + mDataset.get(position).numero;
        holder.textView.setText(text);

    }


    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
