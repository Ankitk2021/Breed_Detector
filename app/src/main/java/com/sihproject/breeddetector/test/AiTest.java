package com.sihproject.breeddetector.test;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.OptIn;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.sihproject.breeddetector.R;
import com.sihproject.breeddetector.pojo.Breed;

import java.util.ArrayList;

import kotlinx.coroutines.DelicateCoroutinesApi;


public class AiTest extends AppCompatActivity {


    @OptIn(markerClass = DelicateCoroutinesApi.class)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ai_test);
         Intent intent = getIntent();
        System.out.println("Entered in New Activity");
        Bundle bundle = intent.getExtras();
        if(bundle != null){
          ArrayList<Breed> BreedList = (ArrayList<Breed>) bundle.get("response");

            System.out.println(BreedList);
            TextView aiTestShow = findViewById(R.id.testResponse);

            aiTestShow.setText(BreedList.toString());
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }
}