package com.example.cocktails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PushDrink extends AppCompatActivity {

    private EditText url;
    private EditText nome;
    private EditText procedura;
    private EditText categoria;
    private EditText bicchiere;
    private EditText IBA;
    private EditText quantita;
    private EditText quantita2;
    private EditText ingrediente;
    private EditText ingrediente2;

    private Button invia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_drink);

        url = findViewById(R.id.push_image_url);
        nome = findViewById(R.id.push_image_name_detail);
        procedura = findViewById(R.id.push_descrizione);
        categoria = findViewById(R.id.categoria);
        bicchiere = findViewById(R.id.bicchiere);
        IBA = findViewById(R.id.IBA);
        quantita = findViewById(R.id.quantita);
        quantita2 = findViewById(R.id.quantita2);
        ingrediente = findViewById(R.id.ingrediente);
        ingrediente2 = findViewById(R.id.ingrediente2);

        invia = findViewById(R.id.invia);
        invia.setOnClickListener(v -> {
            postDrink();
        });

    }


    private void postDrink() {
        //    public Drink(/*Id id*/, String idDrink,
        //    String strDrink, Object strDrinkAlternate,
        //    Object strTags, String strCategory, Object strIBA,
        //    String strAlcoholic, String strGlass, String strInstructions,
        //    String strDrinkThumb, String dateModified, List<Ingredient> ingredients) {
        List<Ingredient> ingredients= new ArrayList<>();
        Ingredient zucchero= new Ingredient("zucchero", "20 cucchiai");
        //ingredients.add(zucchero);
        Ingredient i1=new Ingredient(ingrediente.getText().toString(),quantita.getText().toString());
        Ingredient i2=new Ingredient(ingrediente2.getText().toString(),quantita2.getText().toString());
        ingredients.add(i1);
        ingredients.add(i2);

        Date currentTime = Calendar.getInstance().getTime();
        Drink drink1= new Drink();
        drink1.setIdDrink("");
        drink1.setStrDrink(nome.getText().toString());
        drink1.setStrDrinkAlternate("");
        drink1.setStrTags("");
        drink1.setStrCategory(categoria.getText().toString());
        drink1.setStrIBA(IBA.getText().toString());
        drink1.setStrAlcoholic("");
        drink1.setStrGlass(bicchiere.getText().toString());
        drink1.setStrInstructions(procedura.getText().toString());
        drink1.setStrDrinkThumb(url.getText().toString());
        drink1.setDateModified(currentTime.toString());
        drink1.setIngredients(ingredients);


        Drink drink= new Drink("1234", "provaPost", "noh", " ", " ", " ", "alcholic", "glass",
                "Instructions", "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/shutterstock-626261780mod-1515166546.jpg",
                currentTime.toString(), ingredients);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api= retrofit.create(Api.class);

        Call<Drink> call = api.createPost(drink1);

        call.enqueue(new Callback<Drink>() {
            @Override
            public void onResponse(Call<Drink> call, Response<Drink> response) {
                if(!response.isSuccessful()){
                    System.out.println("Response code::"+ response.code());
                    return;
                }
                //Drink drinkResponse = response.body();
            }


            @Override
            public void onFailure(Call<Drink> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

        finish();
    }
}
