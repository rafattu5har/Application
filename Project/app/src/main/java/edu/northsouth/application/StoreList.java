package edu.northsouth.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StoreList extends AppCompatActivity {

    private ListView storeListView;
    private FirebaseAuth mAuth; // Create FireBase object for Authentication
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;

    private List<Store> storeLst;
    private CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_list);
        this.setTitle("Select Store");

        mAuth = FirebaseAuth.getInstance();// mAuth comment
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("businessid");

        storeLst = new ArrayList<>();
        customAdapter = new CustomAdapter(StoreList.this,storeLst);

        storeListView = (ListView) findViewById(R.id.storeListView);

    }

    @Override
    protected void onStart() {

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                storeLst.clear();

                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    Store store = ds.getValue(Store.class);
                    storeLst.add(store);
                }

                storeListView.setAdapter(customAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        super.onStart();
    }
}
