package com.example.cocktails;

import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Drink {

    @SerializedName("_id")
    @Expose
    private Id id;
    @SerializedName("idDrink")
    @Expose
    private String idDrink;
    @SerializedName("strDrink")
    @Expose
    private String strDrink;
    @SerializedName("strDrinkAlternate")
    @Expose
    private Object strDrinkAlternate;
    @SerializedName("strTags")
    @Expose
    private Object strTags;
    @SerializedName("strCategory")
    @Expose
    private String strCategory;
    @SerializedName("strIBA")
    @Expose
    private Object strIBA;
    @SerializedName("strAlcoholic")
    @Expose
    private String strAlcoholic;
    @SerializedName("strGlass")
    @Expose
    private String strGlass;
    @SerializedName("strInstructions")
    @Expose
    private String strInstructions;
    @SerializedName("strDrinkThumb")
    @Expose
    private String strDrinkThumb;
    @SerializedName("dateModified")
    @Expose
    private String dateModified;
    @SerializedName("ingredients")
    @Expose
    private List<Ingredient> ingredients = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Drink() {
    }

    /**
     *
     * @param dateModified
     * @param idDrink
     * @param strTags
     * @param strCategory
     * @param strDrink
     * @param strIBA
     * @param strAlcoholic
     * @param ingredients
     * @param strDrinkThumb
     * @param strInstructions
     * @param strDrinkAlternate
     * @param strGlass
     */
    public Drink( String idDrink, String strDrink, Object strDrinkAlternate, Object strTags, String strCategory, Object strIBA, String strAlcoholic, String strGlass, String strInstructions, String strDrinkThumb, String dateModified, List<Ingredient> ingredients) {
        super();
        //this.id = id;
        this.idDrink = idDrink;
        this.strDrink = strDrink;
        this.strDrinkAlternate = strDrinkAlternate;
        this.strTags = strTags;
        this.strCategory = strCategory;
        this.strIBA = strIBA;
        this.strAlcoholic = strAlcoholic;
        this.strGlass = strGlass;
        this.strInstructions = strInstructions;
        this.strDrinkThumb = strDrinkThumb;
        this.dateModified = dateModified;
        this.ingredients = ingredients;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Drink withId(Id id) {
        this.id = id;
        return this;
    }

    public String getIdDrink() {
        return idDrink;
    }

    public void setIdDrink(String idDrink) {
        this.idDrink = idDrink;
    }

    public Drink withIdDrink(String idDrink) {
        this.idDrink = idDrink;
        return this;
    }

    public String getStrDrink() {
        return strDrink;
    }

    public void setStrDrink(String strDrink) {
        this.strDrink = strDrink;
    }

    public Drink withStrDrink(String strDrink) {
        this.strDrink = strDrink;
        return this;
    }

    public Object getStrDrinkAlternate() {
        return strDrinkAlternate;
    }

    public void setStrDrinkAlternate(Object strDrinkAlternate) {
        this.strDrinkAlternate = strDrinkAlternate;
    }

    public Drink withStrDrinkAlternate(Object strDrinkAlternate) {
        this.strDrinkAlternate = strDrinkAlternate;
        return this;
    }

    public Object getStrTags() {
        return strTags;
    }

    public void setStrTags(Object strTags) {
        this.strTags = strTags;
    }

    public Drink withStrTags(Object strTags) {
        this.strTags = strTags;
        return this;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public Drink withStrCategory(String strCategory) {
        this.strCategory = strCategory;
        return this;
    }

    public Object getStrIBA() {
        return strIBA;
    }

    public void setStrIBA(Object strIBA) {
        this.strIBA = strIBA;
    }

    public Drink withStrIBA(Object strIBA) {
        this.strIBA = strIBA;
        return this;
    }

    public String getStrAlcoholic() {
        return strAlcoholic;
    }

    public void setStrAlcoholic(String strAlcoholic) {
        this.strAlcoholic = strAlcoholic;
    }

    public Drink withStrAlcoholic(String strAlcoholic) {
        this.strAlcoholic = strAlcoholic;
        return this;
    }

    public String getStrGlass() {
        return strGlass;
    }

    public void setStrGlass(String strGlass) {
        this.strGlass = strGlass;
    }

    public Drink withStrGlass(String strGlass) {
        this.strGlass = strGlass;
        return this;
    }

    public String getStrInstructions() {
        return strInstructions;
    }

    public void setStrInstructions(String strInstructions) {
        this.strInstructions = strInstructions;
    }

    public Drink withStrInstructions(String strInstructions) {
        this.strInstructions = strInstructions;
        return this;
    }

    public String getStrDrinkThumb() {
        return strDrinkThumb;
    }

    public void setStrDrinkThumb(String strDrinkThumb) {
        this.strDrinkThumb = strDrinkThumb;
    }

    public Drink withStrDrinkThumb(String strDrinkThumb) {
        this.strDrinkThumb = strDrinkThumb;
        return this;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public Drink withDateModified(String dateModified) {
        this.dateModified = dateModified;
        return this;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Drink{" +
                "id=" + id +
                ", idDrink='" + idDrink + '\'' +
                ", strDrink='" + strDrink + '\'' +
                ", strDrinkAlternate=" + strDrinkAlternate +
                ", strTags=" + strTags +
                ", strCategory='" + strCategory + '\'' +
                ", strIBA=" + strIBA +
                ", strAlcoholic='" + strAlcoholic + '\'' +
                ", strGlass='" + strGlass + '\'' +
                ", strInstructions='" + strInstructions + '\'' +
                ", strDrinkThumb='" + strDrinkThumb + '\'' +
                ", dateModified='" + dateModified + '\'' +
                ", ingredients=" + ingredients +
                '}';
    }

    public Drink withIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

}