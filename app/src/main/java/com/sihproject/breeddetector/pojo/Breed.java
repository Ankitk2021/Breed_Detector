package com.sihproject.breeddetector.pojo;

import java.io.Serializable;

public class Breed implements Serializable {
   private String name;
    private String cattleType ;
    private String accuracy;
    private String cattleDesc;
    private String food;
    private  String possibleDieses;

    private String imageUrl;

    public Breed(String name, String cattleType,String accuracy, String cattleDesc, String food, String possibleDieses, String imageUrl) {
        this.name = name;
        this.cattleType = cattleType;
        this.accuracy = accuracy;
        this.cattleDesc = cattleDesc;
        this.food = food;
        this.possibleDieses = possibleDieses;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCattleType() {
        return cattleType;
    }

    public void setCattleType(String cattleType) {
        this.cattleType = cattleType;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }

    public String getCattleDesc() {
        return cattleDesc;
    }

    public void setCattleDesc(String cattleDesc) {
        this.cattleDesc = cattleDesc;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getPossibleDieses() {
        return possibleDieses;
    }

    public void setPossibleDieses(String possibleDieses) {
        this.possibleDieses = possibleDieses;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Breed{" +
                "name='" + name + '\'' +
                ", cattleType='" + cattleType + '\'' +
                ", accuracy=" + accuracy +
                ", cattleDesc='" + cattleDesc + '\'' +
                ", food='" + food + '\'' +
                ", possibleDieses='" + possibleDieses + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
