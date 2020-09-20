package com.example.yourdestination.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yourdestination.R;
import com.example.yourdestination.model.PhotoDetailModelClass;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageExplorerAdapter extends RecyclerView.Adapter<ImageExplorerAdapter.ImageViewHolder> {
    private Context context;
    private ArrayList<PhotoDetailModelClass> imageList;
    LayoutInflater inflater;

    public ImageExplorerAdapter(Context context, ArrayList<PhotoDetailModelClass> imageList) {
        this.context = context;
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_explore, parent, false);

        return new ImageViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Picasso.get().load(imageList.get(position).getPhotoUrl()).into(holder.imageView);
        holder.textView.setText(imageList.get(position).getPhotoName());
        holder.ImageDesc.setText(imageList.get(position).getPhotoDesc());


    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }



    public class ImageViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView , ImageDesc;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.rImageView);
            textView = itemView.findViewById(R.id.rTextTittle);
            ImageDesc = itemView.findViewById(R.id.rPhotoDesc);
        }
    }

}
