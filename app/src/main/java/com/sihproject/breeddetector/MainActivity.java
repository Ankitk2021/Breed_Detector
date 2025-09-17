package com.sihproject.breeddetector;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.content.FileProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.sihproject.breeddetector.adapter.HistoryAdapter;
import com.sihproject.breeddetector.adapter.SpinnerHelper;
import com.sihproject.breeddetector.configuration.AiModel;
import com.sihproject.breeddetector.helper.AiCallback;
import com.sihproject.breeddetector.helper.AiHelperKt;
import com.sihproject.breeddetector.pojo.Breed;
import com.sihproject.breeddetector.adapter.RecyclerAdapter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final int openCameraCode = 120;
    private final int openGalleryCode = 151;

    private ActivityResultLauncher<Intent> galleryLauncher;

    ArrayList<Breed> BreedList = new ArrayList<>();
    ArrayList<Breed> historyList = new ArrayList<>();
    private ActivityResultLauncher<Intent> cameraLauncher;
    Uri selectedImageUri;
    String option = "Tell about the animal/cattle/anything in the image, use google search or anything before responding.";
    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextView loadingText = findViewById(R.id.loadingTex);
        ImageView cameraLogo = findViewById(R.id.camera);
        DrawerLayout drawerLayout = findViewById(R.id.main);
        ImageView gallery = findViewById(R.id.gallery);
        ImageView profile = findViewById(R.id.profile);
        ImageView recent = findViewById(R.id.recent);
        ImageView comingImgPrev = findViewById(R.id.comingPhoto);
        RecyclerView responseList = findViewById(R.id.responseList);
        Button getResponse = findViewById(R.id.getResBtn);
        LinearLayout Camera_Galley_Container = findViewById(R.id.Camera_Gallery_Container);
        LinearLayout home_main_container = findViewById(R.id.main_container);
        LinearLayout fabBtn = findViewById(R.id.fab_btn);
        TextView history_placeholder = findViewById(R.id.history_placeholder);
        RecyclerView history_recyler_view = findViewById(R.id.historyList);
        LinearLayout branding_layout = findViewById(R.id.branding_layout);
        LinearLayout response_layout = findViewById(R.id.response_layout);
        // AppCompatSpinner cattle_select_spinner = findViewById(R.id.cattleType_spinner);


//        ArrayList<String> cattleType_list = SpinnerHelper.getSpinnerList();
//        ArrayAdapter<String> spinner_adapter = SpinnerHelper.getAdapter(this, android.R.layout.simple_list_item_1, cattleType_list);
//        cattle_select_spinner.setAdapter(spinner_adapter);

        HistoryAdapter historyAdapter = new HistoryAdapter(historyList, this);

        AiCallback callback_interface = new AiCallback() {


            @Override
            public void onSuccess(ArrayList<Breed> breeds) {
                historyList.add(breeds.get(0));

                RecyclerAdapter adapter = new RecyclerAdapter(breeds, MainActivity.this, bitmap);
                responseList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                responseList.setAdapter(adapter);
                loadingText.setTextSize(20);
                historyAdapter.notifyDataSetChanged();
                if (historyList.isEmpty()) {
                    loadingText.setText("Something went wrong. ☹️ Please try again later.");
                    history_placeholder.setVisibility(VISIBLE);
                    history_recyler_view.setVisibility(GONE);
                } else {
                    loadingText.setVisibility(GONE);
                    history_placeholder.setVisibility(GONE);
                    history_recyler_view.setVisibility(VISIBLE);
                }
            }

            @Override
            public void onFailure(String e) {
                Toast.makeText(MainActivity.this, e, Toast.LENGTH_SHORT).show();

                loadingText.setText(e);
                loadingText.setTextSize(10);
                loadingText.setBackgroundResource(R.drawable.error_bg);
                if (historyList.isEmpty()) {
                    history_placeholder.setVisibility(VISIBLE);
                    history_recyler_view.setVisibility(GONE);
                } else {
                    history_placeholder.setVisibility(GONE);
                    history_recyler_view.setVisibility(VISIBLE);
                }
            }
        };

        history_recyler_view.setLayoutManager(new LinearLayoutManager(this));
        history_recyler_view.setAdapter(historyAdapter);


//        cattle_select_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//
//                option = parent.getItemAtPosition(position).toString();
//
//
//                if (!option.isEmpty()) {
//                    System.out.println(option);
//                    Toast.makeText(MainActivity.this, "Cattle Option is selected.", Toast.LENGTH_SHORT).show();
//                    cattle_select_spinner.setVisibility(GONE);
//                    getResponse.setVisibility(VISIBLE);
//                }
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                cattle_select_spinner.setVisibility(VISIBLE);
//                getResponse.setVisibility(GONE);
//            }
//        });

        galleryLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        selectedImageUri = result.getData().getData();
                        Camera_Galley_Container.setVisibility(VISIBLE);
                        home_main_container.setVisibility(GONE);
                        fabBtn.setVisibility(GONE);
                        comingImgPrev.setImageURI(selectedImageUri);
                        comingImgPrev.setVisibility(VISIBLE);
                        cameraLogo.setImageResource(R.drawable.camera);
                        loadingText.setVisibility(GONE);
                        // getResponse.setVisibility(GONE);
                        //cattle_select_spinner.setVisibility(VISIBLE);
                        responseList.setVisibility(GONE);
                        if (historyList.isEmpty()) {
                            history_placeholder.setVisibility(VISIBLE);
                            history_recyler_view.setVisibility(GONE);
                        } else {
                            history_placeholder.setVisibility(GONE);
                            history_recyler_view.setVisibility(VISIBLE);
                        }

                    }
                }
        );

        cameraLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            System.out.println("Let's check response....");
            if (result.getResultCode() == RESULT_OK) {
                // Show the image in your ImageViews using the same Uri
                Camera_Galley_Container.setVisibility(VISIBLE);
                home_main_container.setVisibility(GONE);
                fabBtn.setVisibility(GONE);
                comingImgPrev.setImageURI(selectedImageUri);
                comingImgPrev.setVisibility(VISIBLE);
                //  getResponse.setVisibility(GONE);
                //cattle_select_spinner.setVisibility(VISIBLE);
                cameraLogo.setImageResource(R.drawable.retake);
                responseList.setVisibility(GONE);
                loadingText.setVisibility(GONE);

                if (historyList.isEmpty()) {
                    history_placeholder.setVisibility(VISIBLE);
                    history_recyler_view.setVisibility(GONE);

                } else {
                    history_placeholder.setVisibility(GONE);
                    history_recyler_view.setVisibility(VISIBLE);

                }
            }
        });
        cameraLogo.setOnClickListener(v -> {
            System.out.println("clicked on camera..");
            try {
                File photoFile = File.createTempFile("Captured:", ".jpg", getExternalFilesDir(Environment.DIRECTORY_PICTURES));
                selectedImageUri = FileProvider.getUriForFile(this, getPackageName() + ".provider", photoFile);
                System.out.println("Opening Camera..");
                Intent openCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                openCamera.putExtra(MediaStore.EXTRA_OUTPUT, selectedImageUri);
                cameraLauncher.launch(openCamera);

            } catch (IOException e) {
                e.printStackTrace();

            }

        });
        gallery.setOnClickListener(v -> {

            Intent openGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            galleryLauncher.launch(openGallery);

        });
        profile.setOnClickListener(v -> {
                    Toast.makeText(this, "The app is in prototype stage; The profile displayed is a mock and does not represent real data", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, profile.class);
                    startActivity(intent);
                }
        );
        recent.setOnClickListener(v -> {

            if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.openDrawer(GravityCompat.START);
            } else {
                drawerLayout.closeDrawer(GravityCompat.START);

            }

        });

        getResponse.setOnClickListener(v -> {
            fabBtn.setVisibility(VISIBLE);
            branding_layout.setVisibility(GONE);
            home_main_container.setVisibility(VISIBLE);
            BreedList.clear();
            response_layout.setVisibility(VISIBLE);
            Camera_Galley_Container.setVisibility(GONE);
            responseList.setVisibility(VISIBLE);


            loadingText.setVisibility(VISIBLE);
            loadingText.setTextSize(20);
            loadingText.setText("Please wait. Loading....");
            loadingText.setBackgroundResource(android.R.color.transparent);

            System.out.println("Recycler view has been shown.");


            try {
                if (selectedImageUri != null) {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);

                    if (bitmap != null) {
                        AiModel.getTextResponse(bitmap, option, BreedList, callback_interface);
                    }
                }
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();

               // throw new RuntimeException(e);
            }

            if (historyList.isEmpty()) {
                history_placeholder.setVisibility(VISIBLE);
                history_recyler_view.setVisibility(GONE);
            } else {
                history_placeholder.setVisibility(GONE);
                history_recyler_view.setVisibility(VISIBLE);
            }


        });


    }

}