package com.example.cocktails;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecyclerViewAdapter extends  RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private List<BriefDrink> cocktails;
    private Context mContext;
    private MainActivity mainActivity;
    private Bundle bundle = new Bundle();

    public RecyclerViewAdapter(ArrayList<String> mImageNames, ArrayList<String> mImages, Context mContext, List<BriefDrink> cocktails, MainActivity mainActivity) {
        this.mImageNames = mImageNames;
        this.mImages = mImages;
        this.mContext = mContext;
        this.cocktails= cocktails;
        this.mainActivity= mainActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder,final int i) {
        Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(i))
                .into(viewHolder.image);

        viewHolder.imageName.setText(mImageNames.get(i));

        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExecutorService executor = Executors.newSingleThreadExecutor();
                startRetrofitRequest(i);

            }
        });
    }


    private void startRetrofitRequest(int i){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api= retrofit.create(Api.class);

        Call<List<Drink>> call = api.getCocktail(cocktails.get(i).getId().get$oid());

        call.enqueue(new Callback<List<Drink>>() {
            @Override
            public void onResponse(Call<List<Drink>> call, Response<List<Drink>> response) {

                List<Drink> cocktail = response.body();

                try {
                if(cocktail.get(0).getStrCategory()!=null)
                    bundle.putString("category", cocktail.get(0).getStrCategory());
                if(cocktail.get(0).getStrIBA()!=null )
                    bundle.putString("IBA", cocktail.get(0).getStrIBA().toString());
                if(cocktail.get(0).getStrGlass()!=null )
                    bundle.putString("Bicchiere", cocktail.get(0).getStrGlass());
                if(cocktail.get(0).getDateModified()!=null )
                    bundle.putString("data", cocktail.get(0).getDateModified());
                if(cocktail.get(0).getStrCategory()!=null )
                    bundle.putString("descrizione", cocktail.get(0).getStrInstructions());
                System.out.println(cocktail.get(0).toString());
                    ArrayList<String> ingredients=new ArrayList<>();

                    for (int i=0; i<cocktail.get(0).getIngredients().size(); i++){
                        ingredients.add(cocktail.get(0).getIngredients().get(i).getIngredient()+": "+cocktail.get(0).getIngredients().get(i).getMeasure());
                    }

                    System.out.println(ingredients.size());
                    bundle.putStringArrayList("ingredienti", ingredients);

                }catch (Exception e){
                    System.out.println("ciao"+e.getMessage());
                }
                startFragment(i);
            }

            @Override
            public void onFailure(Call<List<Drink>> call, Throwable t) {
                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void startFragment(int i){
        FragmentTransaction ft = mainActivity.getSupportFragmentManager().beginTransaction();
        ft.addToBackStack(null);
        ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        DetailFragment detailFragment = new DetailFragment();


        bundle.putString("nomeDrink", cocktails.get(i).getStrDrink());
        bundle.putString("urlDrink", cocktails.get(i).getStrDrinkThumb());
        detailFragment.setArguments(bundle);
        ft.replace(R.id.placeholder, detailFragment);

        ft.commit();
    }

    @Override
    public int getItemCount() {
        return mImageNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView image;
        TextView imageName;
        RelativeLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            imageName = itemView.findViewById(R.id.image_name);
            parentLayout= itemView.findViewById(R.id.parent_layout);
        }
    }
}
