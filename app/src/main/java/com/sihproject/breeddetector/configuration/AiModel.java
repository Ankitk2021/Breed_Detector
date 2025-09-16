package com.sihproject.breeddetector.configuration;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.google.ai.client.generativeai.BuildConfig;
import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.type.GenerateContentResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sihproject.breeddetector.MainActivity;
import com.sihproject.breeddetector.helper.AiCallback;
import com.sihproject.breeddetector.helper.AiHelperKt;
import com.sihproject.breeddetector.pojo.Breed;
import com.sihproject.breeddetector.pojo.Prompt;


import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;

public class AiModel {
    private static final String modelName = "gemini-1.5-flash";
    private static final String apiKey = "AIzaSyAqPPX8ivggkT5MXeBThmMgF9A_mGCOGDU";
    private static ArrayList<Breed> BreedList;
    public interface AiInterface{
        public void seBreedList(ArrayList<Breed> BreedList);
    }
    public static ArrayList<Breed> getTextResponse(Bitmap bitmap,String query, ArrayList<Breed> itemList, AiCallback callback_interface) {


        GenerativeModel model = new GenerativeModel(modelName, apiKey);
        StringBuilder responseText = new StringBuilder();


        BreedList = itemList;
        Thread thread = new Thread(() -> {
            try {

                String prompt = Prompt.getPrompt(query);

                GenerateContentResponse response = BuildersKt.runBlocking(
                        Dispatchers.getIO(), // Context for the coroutine
                        // This lambda is suspend (CoroutineScope, Continuation<GenerateContentResponse>) -> Object
                        (coroutineScope, genResponseContinuation) -> {
                            // Now, 'genResponseContinuation' IS INDEED a Continuation<? super GenerateContentResponse>
                            // because runBlocking is typed to return GenerateContentResponse.
                            return AiHelperKt.testAi(bitmap,prompt, genResponseContinuation);
                        }
                );


                if (response != null  && response.getText() != null && response.getText().charAt(0) != '?') {
                    String rawResponse = response.getText();
                    int start = rawResponse.indexOf('[');
                    int end = rawResponse.lastIndexOf(']');
                    if (start != -1 && end != -1 && end > start) {
                        String jsonArrayString = rawResponse.substring(start, end + 1).trim();
                        Gson gson = new Gson();
                        Type listType = new TypeToken<List<Map<String, Object>>>(){}.getType();
                        List<Map<String, Object>> list = gson.fromJson(jsonArrayString, listType);

                        for (Map<String, Object> obj : list) {
                            String cattleBreed = String.valueOf(obj.get("BreedName"));
                            String cattleType = String.valueOf(obj.get("cattleType"));
                            String accuracy = String.valueOf(obj.get("accuracy"));
                            String cattleDesc = String.valueOf(obj.get("cattleDesc"));
                            String food = String.valueOf(obj.get("food"));
                            String possibleD = String.valueOf(obj.get("possibleDieses"));
                            String imageUrl = String.valueOf(obj.get("imageUrl"));

                            BreedList.add(new Breed(cattleBreed, cattleType, accuracy, cattleDesc, food, possibleD, imageUrl));
                        }

                        new Handler(Looper.getMainLooper()).post(() -> {
                         if(!BreedList.isEmpty()){
                             Breed b = BreedList.get(0);
                             if(b.getName().equals("?") || b.getName().charAt(0)== '?'){
                                 callback_interface.onFailure(b.getCattleDesc());
                             }else {
                                 callback_interface.onSuccess(BreedList);
                             }
                         }else {
                             System.out.println(response.getText());
                             callback_interface.onFailure(response.getText());
                         }
                        });

                    } else {
                        new Handler(Looper.getMainLooper()).post(() -> {
                            System.out.println(response.getText());
                            callback_interface.onFailure(response.getText());
                        });
                    }
                } else {
                    new Handler(Looper.getMainLooper()).post(() -> {
                        callback_interface.onFailure("Response is null");
                    });
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
                new Handler(Looper.getMainLooper()).post(() -> {
                    System.out.println(e.getMessage());
                    callback_interface.onFailure("AI Model is not responding properly. 🤖");
                });

                throw new RuntimeException(e);

            }
            if (responseText == null) {
                System.out.println("Empty");
            } else {
                System.out.println("Response has been got.");
            }

        });
        thread.start();


        return BreedList;
    }
}
