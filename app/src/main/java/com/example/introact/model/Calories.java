package com.example.introact.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public final class Calories implements Serializable {

    @SerializedName("calories")
    private String calories;
    @SerializedName("carbs")
    private String carbs;
    @SerializedName("Fat")
    private String Fat;
    @SerializedName("protein")
    private String protein;

    public Calories(){

    }

    public Calories(String calories, String carbs,String Fat ,String protein){
        this.calories = calories;
        this.carbs = carbs;
        this.Fat = Fat;
        this.protein = protein;
    }

    public String getFat() {
        return Fat;
    }

    public void setFat(String Fat) {
        this.Fat = Fat;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getCarbs() {
        return carbs;
    }

    public void setCarbs(String carbs) {
        this.carbs = carbs;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }
}
