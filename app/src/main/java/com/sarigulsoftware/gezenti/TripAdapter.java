package com.sarigulsoftware.gezenti;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.ObjectHolder> {
    private List<Trip> tripList;
    private Context cxt;
    public TripAdapter(List<Trip> tripList, Context cxt) {
        this.tripList = tripList;
        this.cxt = cxt;
    }

    @NonNull
    @Override
    public TripAdapter.ObjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View objectView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_object,parent,false);
        return new ObjectHolder(objectView);
    }

    @Override
    public void onBindViewHolder(@NonNull TripAdapter.ObjectHolder holder, int position) {
        Trip trip =tripList.get(position);
        Glide.with(cxt).load(trip.getId()).into(holder.tripPhoto);
        holder.tripLocation.setText(trip.getLocation());
        holder.tripDate.setText(trip.getDate());
        holder.tripPoint.setText(trip.getPoint());
    }

    @Override
    public int getItemCount() {
        return tripList.size();
    }

    public class ObjectHolder extends RecyclerView.ViewHolder{
        private ImageView tripPhoto ;
        private TextView tripLocation, tripDate, tripPoint;
        public ObjectHolder(@NonNull View itemView) {
            super(itemView);
            tripPhoto = itemView.findViewById(R.id.tripPhotoImageViewRVO);
            tripLocation = itemView.findViewById(R.id.locationTextViewRVO);
            tripDate = itemView.findViewById(R.id.dateTextViewRVO);
            tripPoint = itemView.findViewById(R.id.pointTextViewRVO);
        }
    }
}
