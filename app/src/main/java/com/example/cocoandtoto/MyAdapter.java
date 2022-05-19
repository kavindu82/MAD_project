package com.example.cocoandtoto;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

   Context context;
   ArrayList<User> list;

    public MyAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.detail,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        User user = list.get(position);
        holder.bName.setText(user.getbName());
        holder.town.setText(user.getTown());
        holder.petAccept.setText(user.getPetAccept());
        holder.petLeft.setText(user.getPetLeft());
        holder.petWatch.setText(user.getPetWatch());
        holder.c_petEme.setText(user.getC_petEme());
        holder.contactP.setText(user.getContactP());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class  MyViewHolder extends RecyclerView.ViewHolder{

       TextView bName ,town , petAccept , petLeft ,petWatch , c_petEme ,contactP;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            bName =itemView.findViewById(R.id.brName);
            town =itemView.findViewById(R.id.prTown);
            petAccept =itemView.findViewById(R.id.prAccept);
            petLeft =itemView.findViewById(R.id.prLeft);
            petWatch =itemView.findViewById(R.id.prWatch);
            c_petEme =itemView.findViewById(R.id.prEme);
            contactP =itemView.findViewById(R.id.prCon);
        }
    }
}
