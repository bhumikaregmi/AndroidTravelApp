package com.example.yourdestination.ui.destination;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yourdestination.R;

import java.util.List;

class DestinationAdapter extends RecyclerView.Adapter<DestinationAdapter.MyViewHolder>{

    Context context;
    List<DestinationModel> destinationModelClassList;

    public DestinationAdapter(Context context, List<DestinationModel> destinationModelClassList) {
        this.context = context;
        this.destinationModelClassList = destinationModelClassList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

//        final DestinationModelClass destinationModelClass = destinationModelClassList.get(position);

        holder.setDataIntoViewHolder(destinationModelClassList.get(position), position);

    }


    @Override
    public int getItemCount() {
        return destinationModelClassList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtVew ,txtDetail;
        ImageView img, heartImg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtVew = itemView.findViewById(R.id.txtName);
            img = itemView.findViewById(R.id.img);
            txtDetail= itemView.findViewById(R.id.detail);
            heartImg = itemView.findViewById(R.id.heartImg);
        }

        public void setDataIntoViewHolder(final DestinationModel destinationModelClass, int position) {
            txtVew.setText((position + 1) + ". " + destinationModelClass.getName());
            img.setImageResource(destinationModelClass.getImage());
            heartImg.setVisibility(View.GONE);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(context, DetailPageActivity.class);
                    i.putExtra("name", destinationModelClass.getName());
                    i.putExtra("img", destinationModelClass.getImage());
                    i.putExtra("detail", destinationModelClass.getDetail());
                    context.startActivity(i);

             Toast.makeText(context, "Selected : "+destinationModelClass.getName(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
