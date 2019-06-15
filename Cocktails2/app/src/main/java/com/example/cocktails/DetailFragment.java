package com.example.cocktails;


import android.app.Dialog;
import android.media.Image;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailFragment extends Fragment {


    private TextView nome;
    private String nomeDrink;
    private String urlDrink;
    private String category;
    private String data;
    private String IBA;
    private String Bicchiere;
    private String procedimento;


    private ImageView image;
    private TextView descrizione;
    private ArrayList<String> quantita;



   private ExpandableListAdapter listAdapter;
   private ExpandableListView expListView;
   private List<String> listDataHeader;
   private HashMap<String, List<String>> listDataChild;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        nomeDrink = bundle.getString("nomeDrink");
        urlDrink = bundle.getString("urlDrink");
        category = bundle.getString("category");
        data = bundle.getString("data");
        IBA = bundle.getString("IBA");
        Bicchiere = bundle.getString("Bicchiere");
        procedimento = bundle.getString("descrizione");
        quantita = bundle.getStringArrayList("ingredienti");
        System.out.println("fatto "+ quantita.size());

        return inflater.inflate(R.layout.detail, parent, false);
    }


    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        nome = getActivity().findViewById(R.id.image_name_detail);
        image = getActivity().findViewById(R.id.image_detail);

        descrizione= getActivity().findViewById(R.id.descrizione);
        descrizione.setText(procedimento);



        // Displaying the user details on the screen
        Glide.with(this).load(urlDrink).into(image);
        nome.setText(nomeDrink);


        // get the listview
        expListView = (ExpandableListView) getActivity().findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this.getContext(), listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

    }

    private void makeDialog(String testo, View view, String title) {
        final Dialog dialog = new Dialog(view.getContext());
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setTitle("Title...");


        TextView text =  dialog.findViewById(R.id.dialog_text);
        text.setText(title+": "+testo);
        ImageView dialog_image =  dialog.findViewById(R.id.dialog_image);
        Glide.with(this).load(urlDrink).into(dialog_image);

        Button dialogButton =  dialog.findViewById(R.id.dialogButtonOK);

        dialogButton.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Categoria");
        listDataHeader.add("Data");
        listDataHeader.add("Bicchiere");
        listDataHeader.add("IBA");
        listDataHeader.add("Quantita");

        // Adding child data
        List<String> data_ = new ArrayList<String>();
        data_.add(data);


        List<String> Categoria_ = new ArrayList<String>();
        Categoria_.add(category);


        List<String> Bicchiere_ = new ArrayList<String>();
        Bicchiere_.add(Bicchiere);


        List<String> IBA_ = new ArrayList<String>();
        IBA_.add(IBA);

        List<String> quantia_= new ArrayList<String>();
        for (int i=0; i<quantita.size(); i++ ){
            quantia_.add( quantita.get(i));
        }

        listDataChild.put(listDataHeader.get(0), Categoria_); // Header, Child data
        listDataChild.put(listDataHeader.get(1), data_);
        listDataChild.put(listDataHeader.get(2), Bicchiere_);
        listDataChild.put(listDataHeader.get(3), IBA_);
        listDataChild.put(listDataHeader.get(4), quantia_);

    }
}
