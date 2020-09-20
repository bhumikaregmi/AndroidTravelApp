package com.example.yourdestination;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.yourdestination.Adapter.ImageExplorerAdapter;
import com.example.yourdestination.model.PhotoDetailModelClass;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ExploreMoreFloat extends AppCompatActivity {
    FloatingActionButton floatingActionButton;
    Toolbar toolbar;
    RecyclerView mRecyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    private DatabaseReference databaseReference;
    public static final String TAG = "firebase";
    private ArrayList<PhotoDetailModelClass> imageList;
    private ImageExplorerAdapter imageExplorerAdapter;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_more_float);

        FloatingActionButton floatingActionButton = findViewById(R.id.flot_btn);


        mRecyclerView = findViewById(R.id.recyclerView);
//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

//        firebaseDatabase = FirebaseDatabase.getInstance();
//        reference = firebaseDatabase.getReference("places");


        progressBar = findViewById(R.id.ExploreProgress);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        String myKey = databaseReference.child("places").push().getKey();
        Log.d(TAG, "key is " + myKey);


        imageList = new ArrayList<>();
        imageList.clear();

        DatabaseReference mydatabaseReference = databaseReference.child("places");
        mydatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Log.d("inside", "snap shot is" + dataSnapshot.toString());

                if (dataSnapshot.toString() != null) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        progressBar.setVisibility(View.GONE);

                        String imgUrl = snapshot.child("photoUrl").getValue().toString();
                        String imgDesc = snapshot.child("photoDesc").getValue().toString();
                        String photoName = snapshot.child("photoName").getValue().toString();


                        PhotoDetailModelClass imageModel = new PhotoDetailModelClass();
                        imageModel.setPhotoDesc(imgDesc);
                        imageModel.setPhotoUrl(imgUrl);
                        imageModel.setPhotoName(photoName);

                        imageList.add(imageModel);
                    }
                    imageExplorerAdapter = new ImageExplorerAdapter(getApplicationContext(), imageList);
                    mRecyclerView.setAdapter(imageExplorerAdapter);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));


                }
                Log.d(TAG, "image list is " + imageList.toString());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ExploreMoreFloat.this, "Go", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ExploreMoreFloat.this, Login.class));

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                // return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

