package com.example.yourdestination;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yourdestination.model.PhotoDetailModelClass;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class Addplaces extends AppCompatActivity {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    DatabaseReference postsRef = ref.child("places");
    DatabaseReference newPostRef = postsRef.push();
    Toolbar toolbar;
    private DatabaseReference mDatabase;
    StorageReference mStorageRef;
    ImageView selectedImage;
    Button chooseImage, upload, logout;
    String imageUrl;
    EditText txtPlace,txtDes;
    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 71;
    FirebaseStorage storage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addplaces);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtPlace = findViewById(R.id.place);
        txtDes = findViewById(R.id.desc);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("Add Places");

        selectedImage = findViewById(R.id.imgView);
        chooseImage = findViewById(R.id.chooseimg);
        upload = findViewById(R.id.uploadimg);
        logout = findViewById(R.id.logoutbtn);

        storage = FirebaseStorage.getInstance();
        mStorageRef = storage.getReference();

        chooseImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        upload.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                System.out.println("I am clicked");
                if (txtPlace.getText().toString().trim().isEmpty()){
                    txtPlace.setError("Enter Place Name");
                    return;
                }

                if (txtDes.getText().toString().trim().isEmpty()){
                    txtDes.setError("Enter Description");
                    return;
                }
                uploadImage();

            }
        });
    }

//logout
    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }

    public void sendDataToFirebase(){

        GpsLocationModel gpsLocationModel = new GpsLocationModel(SplashActivity.lat,SplashActivity.lng);

        Places places = new Places("1","rara lake","test",
                imageUrl,0, gpsLocationModel);



        System.out.println("Places : "+places.toString());

        newPostRef.setValue(places, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                if (databaseError != null) {
                    Toast.makeText(Addplaces.this, "Data could not be saved " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    System.out.println("Data could not be saved " + databaseError.getMessage());
                } else {
                    Toast.makeText(Addplaces.this, "Data saved successfully.", Toast.LENGTH_SHORT).show();
                    System.out.println("Data saved successfully.");
                }
            }
        });

    }


    //Upload Image
    public void uploadImage(){
        upload.setEnabled(false);
        upload.setBackgroundColor(getResources().getColor(R.color.green));
        final StorageReference riversRef = mStorageRef.child(new StringBuilder("images/").append(UUID.randomUUID().toString())
                .toString());

        riversRef.putFile(filePath)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>(){
                            @Override
                            public void onSuccess(Uri uri) {
                                System.out.println("Uploaded file ===== "+uri.toString());
                                String myUrl = uri.toString();
                                System.out.println("My url is ====="+myUrl);
                                imageUrl = uri.toString();
//                                UploadData("Rara Lake",imageUrl,"Rara",
//                                        "A");



                                UploadData(txtPlace.getText().toString().trim(),imageUrl,txtDes.getText().toString().trim(),
                                        "A");

                            }
                        });
                        // Get a URL to the uploaded content
//                        Uri downloadUrl = taskSnapshot.getUploadSessionUri();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                        System.out.println("Upload failed ===== "+exception.getMessage());
                    }
                });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("on activity result");
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            System.out.println("Filepath = "+filePath);
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                selectedImage.setImageBitmap(bitmap);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                System.out.println("Error result = "+e.getMessage());
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //ImageChoose
    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    //Upload Data
    public void UploadData(String name,String photName,String photoDesc,String detail){
        PhotoDetailModelClass p = new PhotoDetailModelClass();
        p.setPhotoDesc(photoDesc);
        p.setPhotoName(name);
        p.setPhotoUrl(photName);


        newPostRef.setValue(p, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                if (databaseError != null) {
                    Toast.makeText(Addplaces.this, "Data could not be saved " + databaseError.getMessage(),
                            Toast.LENGTH_SHORT).show();
                    System.out.println("Data could not be saved " + databaseError.getMessage());

                    startActivity(new Intent(Addplaces.this, ExploreMoreFloat.class));
                } else {
                    Toast.makeText(Addplaces.this, "Data saved successfully.", Toast.LENGTH_SHORT).show();
                    System.out.println("Data saved successfully.");
                }
            }
        });

    }
}
