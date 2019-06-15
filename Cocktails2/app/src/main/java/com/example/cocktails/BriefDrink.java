package com.example.cocktails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BriefDrink {

    @SerializedName("_id")
    @Expose
    private Id id;
    @SerializedName("strDrink")
    @Expose
    private String strDrink;
    @SerializedName("strDrinkThumb")
    @Expose
    private String strDrinkThumb;

    /**
     * No args constructor for use in serialization
     *
     */
    public BriefDrink() {
    }

    /**
     *
     * @param id
     * @param strDrinkThumb
     * @param strDrink
     */
    public BriefDrink(Id id, String strDrink, String strDrinkThumb) {
        super();
        this.id = id;
        this.strDrink = strDrink;
        this.strDrinkThumb = strDrinkThumb;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public BriefDrink withId(Id id) {
        this.id = id;
        return this;
    }

    public String getStrDrink() {
        return strDrink;
    }

    public void setStrDrink(String strDrink) {
        this.strDrink = strDrink;
    }

    public BriefDrink withStrDrink(String strDrink) {
        this.strDrink = strDrink;
        return this;
    }

    public String getStrDrinkThumb() {
        return strDrinkThumb;
    }

    public void setStrDrinkThumb(String strDrinkThumb) {
        this.strDrinkThumb = strDrinkThumb;
    }

    public BriefDrink withStrDrinkThumb(String strDrinkThumb) {
        this.strDrinkThumb = strDrinkThumb;
        return this;
    }

}