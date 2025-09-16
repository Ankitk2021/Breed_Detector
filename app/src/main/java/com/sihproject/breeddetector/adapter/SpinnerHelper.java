package com.sihproject.breeddetector.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class SpinnerHelper {

    public static ArrayList<String> getSpinnerList() {
        ArrayList<String> cattleTypeList = new ArrayList<>();
        cattleTypeList.add("Select the animal                  ▼");
        cattleTypeList.add("Cow");
        cattleTypeList.add("Buffalo");
        cattleTypeList.add("Goat");
        cattleTypeList.add("Sheep");
        cattleTypeList.add("Horse");
        cattleTypeList.add("Camel");
        cattleTypeList.add("Pig");
        cattleTypeList.add("Donkey");
        cattleTypeList.add("Yak");
        return cattleTypeList;
    }

    public static  ArrayAdapter<String> getAdapter(Context context
            ,int resource_layout, ArrayList<String> cattleList){

        return new ArrayAdapter<>(context,resource_layout,cattleList);
    }
}
