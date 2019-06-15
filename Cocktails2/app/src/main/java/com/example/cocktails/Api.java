package com.example.cocktails;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {

    String BASE_URL = "https://webhooks.mongodb-stitch.com/api/client/v2.0/app/cocktailsapp-tysnh/service/";

    @GET("getDrinks/incoming_webhook/allDrinks")
    Call<List<Drink>> getCocktails();

    @GET("getDrinks/incoming_webhook/getDrinksBriefly")
    Call<List<BriefDrink>> getBriefCocktails();

    @GET("getDrinks/incoming_webhook/allDrinks")
    Call<List<Drink>> getCocktail(@Query("id") String id);

    @POST("InsertDrinks/incoming_webhook/InsertDrink")
    Call<Drink>createPost(@Body Drink drink);
}
