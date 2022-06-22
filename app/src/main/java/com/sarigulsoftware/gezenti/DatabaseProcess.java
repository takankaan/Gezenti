package com.sarigulsoftware.gezenti;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DatabaseProcess {
    private static String userUid = FirebaseAuth.getInstance().getUid();
    private static DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Trips/"+userUid);

    public static void getTripList(Context cxt, RecyclerView recyclerView){
        List<Trip> tripList = new ArrayList<Trip>();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Trip trip = new Trip();
                for(DataSnapshot ds : snapshot.getChildren())
                {
                    trip.setId(ds.child("Id").getValue(String.class));
                    trip.setLocation(ds.child("Location").getValue(String.class));
                    trip.setDate(ds.child("Date").getValue(String.class));
                    trip.setDeclaration(ds.child("Declaration").getValue(String.class));
                    trip.setSummary(ds.child("Summary").getValue(String.class));
                    trip.setPoint(ds.child("Point").getValue(String.class));
                    tripList.add(trip);
                }
                TripAdapter adapter = new TripAdapter(tripList,cxt);
                recyclerView.setAdapter(adapter);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(cxt));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
