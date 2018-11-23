package com.recipe.Model;

import java.io.Serializable;

public class Recipe implements Serializable{

    private long id;
    private String name;
    private String totalTime;
    private String[] ingredients;
    private String totalCal;
    private String fat_kcal;
    private String enerc_fat;
    private String source_url;
    private String course;
    private String cuisine;
    private String photo;

    public Recipe() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public String getTotalCal() {
        return totalCal;
    }

    public void setTotalCal(String totalCal) {
        this.totalCal = totalCal;
    }

    public String getFat_kcal() {
        return fat_kcal;
    }

    public void setFat_kcal(String fat_kcal) {
        this.fat_kcal = fat_kcal;
    }

    public String getEnerc_fat() {
        return enerc_fat;
    }

    public void setEnerc_fat(String enerc_fat) {
        this.enerc_fat = enerc_fat;
    }

    public String getSource_url() {
        return source_url;
    }

    public void setSource_url(String source_url) {
        this.source_url = source_url;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
