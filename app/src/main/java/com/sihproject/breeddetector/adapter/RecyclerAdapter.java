package com.sihproject.breeddetector.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sihproject.breeddetector.BreedPage;
import com.sihproject.breeddetector.pojo.Breed;
import com.sihproject.breeddetector.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.VH> {

    private final ArrayList<Breed> itemList;

    private final Context context;
    Bitmap bitmap;

    public RecyclerAdapter(ArrayList<Breed> breedList, Context context,Bitmap b) {

        itemList = breedList;
        this.context = context;
        this.bitmap = b;


    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.breed_card_layout, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

        Breed breed = itemList.get(position);

        if (breed != null) {
            holder.breedName.setText(breed.getName());
            holder.cattleTypeName.setText(breed.getCattleType());
            String accStr = breed.getAccuracy();
            String accuracy = accStr + "%";
            int acc = 0;
            try {
                acc = Integer.parseInt(accStr);
                acc = acc * 100;
            } catch (Exception e) {
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            holder.accuracy.setBackgroundResource(R.drawable.acc_bg);
            holder.accuracy.setText(accuracy);


            holder.view.setOnClickListener(e -> {

                Intent intent = new Intent(context, BreedPage.class).putExtra("breed_data", breed);
                Uri uri = this.getUri(context,bitmap);
                intent.putExtra("photo",uri.toString());
                context.startActivity(intent);

                Toast.makeText(context, "You Clicked on Item on index " + position, Toast.LENGTH_SHORT).show();
            });

        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class VH extends RecyclerView.ViewHolder {

        TextView breedName, cattleTypeName, accuracy;
        View view;

        public VH(@NonNull View itemView) {
            super(itemView);
            breedName = itemView.findViewById(R.id.breedName);
            cattleTypeName = itemView.findViewById(R.id.cattleType);
            accuracy = itemView.findViewById(R.id.accuracy);
            this.view = itemView;

            itemView.setOnClickListener((e) -> {
                System.out.println("Clicked");

            });
        }
    }

    public Uri getUri(Context context, Bitmap bitmap){

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,bytes);

        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(),bitmap,"MyImage",null);
        return Uri.parse(path);

    }


}
