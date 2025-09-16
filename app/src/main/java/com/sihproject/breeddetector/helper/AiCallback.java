package com.sihproject.breeddetector.helper;

import com.sihproject.breeddetector.pojo.Breed;

import java.util.ArrayList;

public interface AiCallback {
    void onSuccess(ArrayList<Breed> breeds);
    void onFailure(String cause);
}
