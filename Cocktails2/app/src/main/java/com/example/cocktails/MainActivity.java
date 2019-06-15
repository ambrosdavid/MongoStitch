package com.example.cocktails;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private Button push;
    private Button ricarica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        push = findViewById(R.id.push);
        push.setOnClickListener(v -> {
            Intent i = new Intent(v.getContext(), PushDrink.class);
            startActivity(i);
        });

        ricarica = findViewById(R.id.reload);
        ricarica.setOnClickListener(v -> {
            finish();
            startActivity(getIntent());
        });


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api= retrofit.create(Api.class);

        Call<List<BriefDrink>> call = api.getBriefCocktails();

        call.enqueue(new Callback<List<BriefDrink>>() {
            @Override
            public void onResponse(Call<List<BriefDrink>> call, Response<List<BriefDrink>> response) {

                List<BriefDrink> cocktails = response.body();


                for (int i=0; i<cocktails.size(); i++){
                    mNames.add(cocktails.get(i).getStrDrink());
                    mImageUrls.add(cocktails.get(i).getStrDrinkThumb());
                }

                initRecyclerView(cocktails);

            }

            @Override
            public void onFailure(Call<List<BriefDrink>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        //postDrink();
    }




    private void initRecyclerView(List<BriefDrink> cocktails){
        RecyclerView recyclerView= findViewById(R.id.recicleView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter( mNames, mImageUrls, this, cocktails, MainActivity.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }
}
