package com.example.introact.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public final class Fruits implements Serializable {

    @SerializedName("name")
    private String name;
    @SerializedName("category")
    private String category;
    @SerializedName("image")
    private String image;
    @SerializedName("title")
    private String title;
    @SerializedName("recipea")
    private String recipea;
    @SerializedName("recipeb")
    private String recipeb;
    @SerializedName("recipec")
    private String recipec;
    @SerializedName("reciped")
    private String reciped;
    @SerializedName("recipee")
    private String recipee;
    @SerializedName("recipef")
    private String recipef;
    @SerializedName("recipeg")
    private String recipeg;
    @SerializedName("recipeh")
    private String recipeh;
    @SerializedName("ina")
    private String ina;
    @SerializedName("inb")
    private String inb;
    @SerializedName("inc")
    private String inc;
    @SerializedName("ind")
    private String ind;
    public Fruits(){

    }
    public Fruits(String name, String category, String image,String title,String recipea,String recipeb,String recipec,String reciped,String recipee,String recipef,String recipeg,String recipeh,String ina,String inb, String inc,String ind) {
        this.name = name;
        this.category = category;
        this.image = image;
        this.title = title;
        this.recipea = recipea;
        this.recipeb = recipeb;
        this.recipec = recipec;
        this.reciped = reciped;
        this.recipee = recipee;
        this.recipef = recipef;
        this.recipeg = recipeg;
        this.recipeh = recipeh;
        this.ina = ina;
        this.inb = inb;
        this.inc = inc;
        this.ind = ind;
    }

    public String getRecipee() {
        return recipee;
    }

    public void setRecipee(String recipee) {
        this.recipee = recipee;
    }

    public String getRecipef() {
        return recipef;
    }

    public void setRecipef(String recipef) {
        this.recipef = recipef;
    }

    public String getRecipeg() {
        return recipeg;
    }

    public void setRecipeg(String recipeg) {
        this.recipeg = recipeg;
    }

    public String getRecipeh() {
        return recipeh;
    }

    public void setRecipeh(String recipeh) {
        this.recipeh = recipeh;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRecipea() {
        return recipea;
    }

    public void setRecipea(String recipea) {
        this.recipea = recipea;
    }

    public String getRecipeb() {
        return recipeb;
    }

    public void setRecipeb(String recipeb) {
        this.recipeb = recipeb;
    }

    public String getRecipec() {
        return recipec;
    }

    public void setRecipec(String recipec) {
        this.recipec = recipec;
    }

    public String getReciped() {
        return reciped;
    }

    public void setReciped(String reciped) {
        this.reciped = reciped;
    }

    public String getIna() {
        return ina;
    }

    public void setIna(String ina) {
        this.ina = ina;
    }

    public String getInb() {
        return inb;
    }

    public void setInb(String inb) {
        this.inb = inb;
    }

    public String getInc() {
        return inc;
    }

    public void setInc(String inc) {
        this.inc = inc;
    }

    public String getInd() {
        return ind;
    }

    public void setInd(String ind) {
        this.ind = ind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

