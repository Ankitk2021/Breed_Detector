package com.sihproject.breeddetector;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.sihproject.breeddetector.pojo.Breed;

import java.io.Serializable;

public class BreedPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_breed_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView breedImg = findViewById(R.id.breed_page_breedImg);
        TextView breedName, breedDesc, breedFood, breedRog, breedCattleType;
        breedName = findViewById(R.id.breed_page_breedName);
        breedDesc = findViewById(R.id.breed_page_breedDesc);
        breedFood = findViewById(R.id.breed_page_breedFood);
        breedRog = findViewById(R.id.breed_page_breedRog);
        breedCattleType = findViewById(R.id.breed_page_breedCattleType);

        Intent intent = getIntent();
        Uri uri= null;
        Breed breed = (Breed) intent.getExtras().getSerializable("breed_data");
        String uriStr = intent.getStringExtra("photo");

        if(uriStr != null){
            uri = Uri.parse(uriStr);
        }

        if (breed != null) {
            breedName.setText(breed.getName());
            breedDesc.setText(breed.getCattleDesc());
            breedFood.setText(breed.getFood());
            breedRog.setText(breed.getPossibleDieses());
            breedCattleType.setText(breed.getCattleType());
            String cattleType = breed.getCattleType();
            int resource = R.drawable.app_name_bg;

            if (cattleType.equals("Cow")) {
                resource = R.drawable.cow_png;
            } else if (cattleType.equals("Buffalo")) {
                resource = R.drawable.bhains_png;
            } else if (cattleType.equals("Goat")) {
                resource = R.drawable.goat;
            } else if (cattleType.equals("Sheep")) {
                resource = R.drawable.sheep;
            } else if (cattleType.equals("Horse")) {
                resource = R.drawable.horse;
            } else if (cattleType.equals("Donkey")) {
                resource = R.drawable.donkey;
            } else if (cattleType.equals("Pig")) {
                resource = R.drawable.pig;
            } else if (cattleType.equals("Camel")) {
                resource = R.drawable.camel;
            } else if (cattleType.equals("Yak")) {
                resource = R.drawable.yak;
            } else {
                Toast.makeText(this, "Unknown livestock type selected", Toast.LENGTH_SHORT).show();
                resource = R.drawable.app_name_bg;
            }
            breedImg.setImageResource(resource);

            if(uri != null){
                breedImg.setImageURI(uri);
            }

        }

    }
}