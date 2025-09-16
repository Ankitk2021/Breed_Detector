package com.sihproject.breeddetector.pojo;

import android.graphics.Bitmap;

public class Prompt {
    public static String getPrompt(String query) {
        String format = "{\n" +
                "  \"BreedName\": \"string\",\n" +
                "  \"cattleType\": \"string\",\n" +
                "  \"accuracy\": 0,\n" +
                "  \"cattleDesc\": \"string\",\n" +
                "  \"food\": \"string\",\n" +
                "  \"possibleDieses\": \"string\",\n" +
                "  \"imageUrl\": \"string\"\n" +
                "}";

        String prompt =
                "Identify Indian cattle/livestock/buffalo breed from given image/text. " +
                        "Respond ONLY in this JSON format: " + format + ". " +
                        " return as [ ... ] JSON array. " +
                        "Act like a REST API, no conversation. " +
                        "If unsure, return \"?your_error_msg\". " +
                        "Query: " + query + ". " +
                        "Accuracy must be between 1-100 and imageUrl must be valid.";

        System.out.println("Your Prompt : " + prompt);
        return prompt;
    }
}

