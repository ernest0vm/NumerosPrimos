package com.ernestovaldez.androidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    Numero numero;
    ArrayList<Numero> itemList;
    Context context;
    JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent in = getIntent();
        Bundle param = in.getExtras().getBundle("param");
        String paramString = param.getString("data");
        String StartRange = param.getString("StartRange");
        String FinalRange = param.getString("FinalRange");

        TextView lblRange = findViewById(R.id.lblRange);
        String text = "Rango Inicial: " + StartRange + ", Rango Final: " + FinalRange;
        lblRange.setText(text);
        //Log.d("param received: ", paramString);

        try{

        jsonArray = new JSONArray(paramString);

        RecyclerView recycler = findViewById(R.id.rv1);
        recycler.setHasFixedSize(true);
        recycler.setAdapter(null);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recycler.setLayoutManager(layoutManager);

        itemList = new ArrayList<>();



            for (int i = 0; i < jsonArray.length(); i++) {

                numero = new Numero();
                numero.numero = Integer.parseInt(jsonArray.getJSONObject(i).getString("numero"));

                itemList.add(numero);
            }

            RecyclerView.Adapter adapter = new MyAdapter(itemList);

            recycler.setAdapter(adapter);
            Toast.makeText(ListActivity.this, "Numeros primos encontrados: " + itemList.size(), Toast.LENGTH_LONG).show();

        } catch (Throwable t) {
            Log.e("Error", "Could not parse malformed data: \"" + t.getLocalizedMessage() + "\"");
        }
    }
}
