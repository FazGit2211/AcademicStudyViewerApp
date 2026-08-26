package com.fzuleta.academicstudyviewerapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fzuleta.academicstudyviewerapp.models.Career;

import java.util.ArrayList;

public class CareerRecViewAdapter extends RecyclerView.Adapter<CareerRecViewAdapter.ViewHolder> {

    private ArrayList<Career> careers = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Create object view for every item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rec_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTitleCard.setText(careers.get(position).getName());
        holder.txtTitleCareer.setText(careers.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return this.careers.size();
    }

    public void setCareers(ArrayList<Career> careers) {

        this.careers = careers;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitleCard;
        private TextView txtTitleCareer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitleCard = itemView.findViewById(R.id.txtTitleCard);
            txtTitleCareer = itemView.findViewById(R.id.txtTitleCareer);
        }
    }
}
