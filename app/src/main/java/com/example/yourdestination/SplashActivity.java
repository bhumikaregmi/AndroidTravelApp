package com.example.yourdestination;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yourdestination.extra_class.CheckGpsEnable;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    // splash screen time out
    private static int SPLASH_SCREEN_TIMEOUT = 3000;

    ImageView imageView,logo;
    CheckGpsEnable checkGpsEnable;
//    SharedPreferenceClass sharedPreferenceClass;

    public static double lat = 0.0;
    public static double lng = 0.0;

    public LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // status bar color
//        getWindow().setStatusBarColor(Color.parseColor("#ffffff"));

//        sharedPreferenceClass = new SharedPreferenceClass(getApplicationContext());
//        logo = findViewById(R.id.logo);
        try {
            checkGpsEnable = new CheckGpsEnable(SplashActivity.this);
            checkGpsEnable.enableGps();
            
            String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
            String rationale = "Please provide Location and storage permission to get the gps location in this app and to save data.";
            Permissions.Options options = new Permissions.Options()
                    .setRationaleDialogTitle("Info")
                    .setSettingsDialogTitle("Warning");

            Permissions.check(SplashActivity.this, permissions, rationale, options, new PermissionHandler() {
                @Override
                public void onGranted() {

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                           find_Location();
                        }
                    }, SPLASH_SCREEN_TIMEOUT);


                }

                @Override
                public void onDenied(Context context, ArrayList<String> deniedPermissions) {

                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void find_Location() {

        try{
            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            List<String> providers = locationManager.getProviders(true);
            System.out.println("Providers : "+providers);
            for (String provider : providers) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                locationManager.requestLocationUpdates(provider, 1000, 0,
                        new LocationListener() {

                            public void onLocationChanged(Location location) {

                                if (location != null){

                                    lat = Double.parseDouble(String.format("%.7f", location.getLatitude()));
                                    lng = Double.parseDouble(String.format("%.7f", location.getLongitude()));

//                                    updateUserData(lat, lng);

                                }

                            }

                            public void onProviderDisabled(String provider) {
                            }

                            public void onProviderEnabled(String provider) {
                            }

                            public void onStatusChanged(String provider, int status,
                                                        Bundle extras) {
                            }
                        });
                Location location = locationManager.getLastKnownLocation(provider);
                if (location != null) {
//                    Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                    lat = Double.parseDouble(String.format("%.7f", location.getLatitude()));
                    lng = Double.parseDouble(String.format("%.7f", location.getLongitude()));

                    //Toast.makeText(this, "Lat : "+lat+" Lng : "+lng, Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    Intent splashIntent = new Intent(SplashActivity.this, GetStarted.class);
                    startActivity(splashIntent);
                    finish();
//                    updateUserData(lat, lng);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}