package com.tast.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DisplayAdapter extends RecyclerView.Adapter<DisplayAdapter.ViewHolderss> {

    Context context;
    ArrayList<EmailNums> saves;

    DisplayAdapter(ArrayList<EmailNums> saves, Context context){
        this.saves = saves;
        this.context = context;

    }
    @NonNull
    @Override
    public DisplayAdapter.ViewHolderss onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_data, parent, false);
        return new ViewHolderss(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DisplayAdapter.ViewHolderss holder, int position) {
        holder.mEmail.setText(""+saves.get(position).getEmsilId());
        holder.mNumber.setText(""+saves.get(position).getNumber());
    }

    @Override
    public int getItemCount() {
        return saves.size();
    }
    public class ViewHolderss extends RecyclerView.ViewHolder {
        TextView mEmail,mNumber;
        public ViewHolderss(@NonNull View itemView) {
            super(itemView);
            mEmail = itemView.findViewById(R.id.mEmail);
            mNumber = itemView.findViewById(R.id.mNumber);
        }
    }
}
