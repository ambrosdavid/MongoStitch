package com.example.cocktails;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ingredient  {

    @SerializedName("ingredient")
    @Expose
    private String ingredient;
    @SerializedName("measure")
    @Expose
    private String measure;

    /**
     * No args constructor for use in serialization
     *
     */
    public Ingredient() {
    }

    /**
     *
     * @param measure
     * @param ingredient
     */
    public Ingredient(String ingredient, String measure) {
        super();
        this.ingredient = ingredient;
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public Ingredient withIngredient(String ingredient) {
        this.ingredient = ingredient;
        return this;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public Ingredient withMeasure(String measure) {
        this.measure = measure;
        return this;
    }

}