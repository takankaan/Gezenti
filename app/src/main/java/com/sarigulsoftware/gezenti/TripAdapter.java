package com.sarigulsoftware.gezenti;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TripAdapter {
    private List<Trip> tripList;

    public TripAdapter(List<Trip> tripList) {
        this.tripList = tripList;
    }
    public class objectHolder extends RecyclerView.ViewHolder{

        public objectHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
