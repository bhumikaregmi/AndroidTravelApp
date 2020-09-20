package com.example.yourdestination.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.yourdestination.ExploreMoreFloat;
import com.example.yourdestination.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    Button btnClick;
    CarouselView carouselView;


    int[] sampleImages = {R.drawable.lumbini,R.drawable.raralake,R.drawable.stupa};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //final TextView textView = root.findViewById(R.id.text_home);

        btnClick = root.findViewById(R.id.btnClick);
        carouselView = root.findViewById(R.id.carouselView);





        carouselView.setPageCount(sampleImages.length);

        carouselView.setImageListener(imageListener);

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ExploreMoreFloat.class));

       // homeViewModel.getText().observe(this, new Observer<String>() {
           // @Override
           // public void onChanged(@Nullable String s) {
               // textView.setText(s);
            }
        });
        return root;
    }
    //Adapterview
    AdapterViewFlipper adapterViewFlipper;

    int [] IMAGES = {R.drawable.palpa,
            R.drawable.pashupatinath,
            R.drawable.poonhill
    };
    String[] NAMES = {
            "Palpa",
            "Pashupatinatih",
            "Poonhill"
    };


    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);

        }
    };



//    // To set simple images
//    ImageListener imageListener = new ImageListener() {
//        @Override
//        public void setImageForPosition(int position, ImageView imageView) {
//
////            Picasso.with(getApplicationContext()).load(sampleNetworkImageURLs[position]).
// placeholder(sampleImages[0]).error(sampleImages[3]).fit().centerCrop().into(imageView);
//
//            //imageView.setImageResource(sampleImages[position]);
//        }
//    };
//    ImageListener imageListener = new ImageListener() {
//        @Override
//        public void setImageForPosition(int position, ImageView imageView) {
//            imageView.setImageResource(sampleImages[position]);
//        }
//    };

//    ViewListener viewListener = new ViewListener() {
//
//        @Override
//        public View setViewForPosition(int position) {
//            View customView = getLayoutInflater().inflate(R.layout.view_custom, null);
//            //set view attributes here
//
//            return customView;
//        }
//    };
}