package com.example.yourdestination.ui.destination;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yourdestination.R;

import java.util.ArrayList;
import java.util.List;

public class DestinationFragment extends Fragment {

    //    private DestinationViewModel galleryViewModel;
    DestinationAdapter destinationAdapterClass;
    List<DestinationModel> destinationModelClassList;
    RecyclerView placelist;
    View root;

    String[] items = {"Rara Lake", "Tilicho Lake", "Poon Hill", "Mardi Himal", "Annapurna Base Camp",
            "Gosaikunda", "Lumbini", "Pokhara", "Illam Tea Garden", "Palpa"};

    int[] img = {R.drawable.rara,R.drawable.tilicholake, R.drawable.poonhill,
            R.drawable.mardihimal, R.drawable.annapurna,R.drawable.gosaikunda, R.drawable.lumbini, R.drawable.pokhara,
            R.drawable.illamtea, R.drawable.palpa};

    //image details

    String[] details =
            //Rara lake
            {" Rara Lake at 2,990m, is the deepest lake in Nepal and also one of the most pristine. Surrounded by green hills on all sides, covered in juniper trees, one can camp by the sparkling waters of the lake. Go boating in the clear waters, hike to nearby hills for a closer view of the mountains and lake, get acquainted with the charming local people, or just walk around the large lake watching out for wild flowers or a rare bird along the way.\n" +
            "\n" +
            "The park is surrounded by alpine coniferous vegetation and offers a representative sample of the region's flora and fauna." +
            " More than 500 different kinds of flowers, 20 species of mammals and 214 species of birds can be observed in the Rara National Park." +
            " As for life in the lake, the snow trout is one of the fish varieties recorded here so far.",

                   // Tilicho Lake
            "Tilicho Lake  at an altitude of (4,949m) is a well-known place for pilgrimages. \n" +
                    "Hence, as we go to the high altitude to triumph over the view of the lake is not an easy task, but this trip is truly exciting, " +
                    "thrilling and more or less rewarding. Though trekking Tilicho Lake is a challenging trekking route, it one of the finest trekking " +
                    "routes in the Annapurna region and easy, compared to Treks in Everest and Kanchenjunga region. " +
                    "This trek displays a scenic view of much of the eastern side of the epic world-famous Annapurna circuit with sublime natural beauty," +
                    " cultural and traditional values, \n" +
            "stunning waterfalls and running clean water streams, and attractive floras and faunas.",

                    //Poon Hill
            " Offering epic Himalayan views, Poon Hill (3210m / 10,532 ft) is a popular destination for trekkers in the Annapurna region of Nepal." +
                    " Most people arrive here in the early morning to enjoy the beautiful sunrise views.Poon Hill is among Nepal's most popular view points. " +
                    "Most trekkers hike to Poon Hill from the village of Ghorepani in the early morning to see sunrise. " +
                    "The views on Annapurna and Dhaulagiri are spectacular and well worth the hour-long early morning climb along stone stairs through rhododendron forest",


                    //Mardi Himal
            " Mardi Himal trek was accessed only by limited trekking groups with tents and catering team for long time. " +
                    "The trek follows beaten trails of shepherds as it still provides summer grazing. The trek was officially opened in 2012 and is still pristine. " +
                    "There are limited teahouses with basic facilities. This is a short relatively easy and unspoiled trek not far from Pokhara. Yet it is highly diverse" +
                    " with cultural insight, beautiful forest walks and alpine landscape.",


                    //Annapurna Base Camp
            " There are few treks that combine so many different landscapes and bring you so close to the base of 7,000 and 8,000 meter " +
                    "peaks in a such a short period of time as the Annapurna Base Camp Trek.\n" +
            "The Annapurna Base Camp trek is one of the most popular treks in the Annapurna region. " +
            "The trail goes alongside terraced rice paddies, lush rhododendron forests and high altitude landscapes with the Annapurna Range in view most of the times.\n" +
            "\n" + "The setting of Annapurna Base Camp at 4130 m is unique and incredibly spectacular, set amidst the majestic peaks of Annapurna I (8091 m), " +
            "Annapurna South (7219 m), Machapuchhre (6993 m) and Hiunchuli (6441 m).\n" +
            "The Annapurna Base Camp Trek takes 7 to 12 days, depending on your itinerary and length of walking days. ",

                   // Gosainkunda
            " Gosainkunda trek begins right from Shivapuri national park in the northern rim of Kathmandu valley providing the magnificent view " +
                    "of the snow peaks from the first day itself. Walk through the subtropical forest to alpine zone with Rhododendron, Oak, pine and birch, finally climbing above the tree line. The trek could be combined with Surya peak (5,100 m) trekking / climbing, Langtang valley trek and Ama Yangri peak (3,771 m) climbing in Helambu.\n" +
                    "Gosainkunda Lake at 4380 m /14368 ft is one of spectacular trekking destination, reached with short trek from Kathmandu.",

                    //Lumbini
            " Nepal is the birth place of Lord Buddha, Siddhartha Gautama. " +
                    "Today you can still see ruins and the exact spot of his birth marked and enclosed by a temple. " +
                    "The tank next to the Maya Devi temple is said to be where his mother bathed before delivering the miraculous baby, born walking and talking." +
                    "The birth site is celebrated with an international peace park. A large rectangular plot where no construction is allowed other" +
                    " than places of religious significance.\n" + "\n" + "Those places of religious significance, built by a multitude of nations, are rather spectacular.",

                    //Pokhara
            " Pokhara is Nepal's number 1 adventure and leisure destination, a gateway to treks in the Annapurna region with plenty of" +
                    " entertainment for individual travellers and their families. \n" + "\n" +
            "In Pokhara you can experience the excitement of adventure: boating, hiking, pony rides, paragliding, bungee, zipline or " +
                    "simply relaxing at one of the several lakes in the valley. Pokhara is well-known as the starting point for numerous trekking" +
                    " trails and expeditions in the Annapurna . Most trekkers heading for the Annapurna region make Pokhara their first stop, " +
                    "or as a relaxing station before heading out for some serious hiking.",


                    //Illam Tea Garden
            " A brisk walk in a tea garden, picnic in the peaceful setting and sightseeing stops," +
                    " short hikes along gentle hills, or explore the nearby woods – enjoy all this and more in the greenery of Ilam." +
                    "Elevations in Ilam district range between 140 m to 3,636 m above sea level. Ilam is sometimes called Charkhol (area of four rivers)" +
                    " because of the four main rivers - the Jogmai, Puwamai, Mai, and Deaumai in the district.",


                    //Palpa
            " Palpa District is not far from Pokhara and easily reached by bus. " +
                    "The ancient hill town of Tansen in this district offers a more tranquil atmosphere where time moves slowly and the laid back lifestyle is infectious. " +
                    "Walk down the cobbled streets in the old bazaar to discover a way of life quite different from modern Pokhara's Lakeside." +
                    " Explore the Shreenagar Hills or go for a day hike to the Rani Mahal, once a vibrant palace on the banks of the Kali Gandaki River."};





    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       /* galleryViewModel =
                ViewModelProviders.of(this).get(DestinationViewModel.class);*/
        root = inflater.inflate(R.layout.fragment_destination, container, false);
        placelist = root.findViewById(R.id.list_view);

        destinationModelClassList = new ArrayList<>();

        for (int i = 0; i < img.length; i++) {
            destinationModelClassList.add(new DestinationModel(items[i], img[i] ,details[i]));
        }


        destinationAdapterClass = new DestinationAdapter(getActivity(), destinationModelClassList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        placelist.setLayoutManager(layoutManager);
        placelist.setHasFixedSize(true);
        placelist.setAdapter(destinationAdapterClass);
        destinationAdapterClass.notifyDataSetChanged();


        return root;
    }
}