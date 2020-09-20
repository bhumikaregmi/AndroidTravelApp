package com.example.yourdestination;

        import android.content.Context;
        import android.view.View;
        import android.widget.ImageView;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder{

    View view;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        view = itemView;
    }

    public void setdetails(Context context, String photoDesc, String photoName){
        TextView mText= view.findViewById(R.id.rTextTittle);
        ImageView mphotoDesc = view.findViewById(R.id.rImageView);

        mText.setText(photoDesc);
        Picasso.get().load(photoName).into(mphotoDesc);
    }
}
