package com.example.manan.javaapp;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class DatabaseHelpers {
    private static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private static DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("user");
    private static final String KEY = mAuth.getCurrentUser().getUid();
    private static int week = -1;

    private static String getCurrUserKey() {
        return KEY;
    }

    public static int getCurrentUserWeek() {

        mRef.child(KEY).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    System.out.println(dataSnapshot);
                    User currUser = dataSnapshot.getValue(User.class);
                    week = currUser.getWeek();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getMessage());
            }
        });
        return week;
    }

    public static void updateCurrentUserWeek(int week) {
        System.out.println("++ Updating user " + KEY + "'s week to: " + week);
        DatabaseReference userRef = mRef.child(KEY);
        Map<String, Object> userUpdates = new HashMap<>();
        userUpdates.put("week", week);
        userRef.updateChildren(userUpdates);
    }

}
