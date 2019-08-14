package com.ernestovaldez.androidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {


    Context context;
    Intent GoToList;
    TextView validator1;
    TextView validator2;
    TextInputEditText txtInput1;
    TextInputEditText txtInput2;
    String StartValue;
    String FinalValue;
    String jsonResponse;
    JSONArray jsonArray;
    Numero numero;
    ArrayList<Numero> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();
        validator1 = findViewById(R.id.lblVal1);
        validator2 = findViewById(R.id.lblVal2);
        txtInput1 = findViewById(R.id.input1);
        txtInput2 = findViewById(R.id.input2);

        Toast.makeText(context, isConnected(context)?"Connected":"No Connected", Toast.LENGTH_LONG).show();

        Button btnFind = findViewById(R.id.btnFind);
        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validator1.setText("");
                validator2.setText("");

//                if (txtInput1.getText() == null || txtInput1.getText().toString().equals(" ")){
//                    validator1.setText(R.string.app_EmptyValue);
//                    return;
//                }
//
//                if (txtInput2.getText() == null || txtInput2.getText().toString().equals(" ")){
//                    validator2.setText(R.string.app_EmptyValue);
//                    return;
//                }

               if(ValidateInput(txtInput1.getText().toString())){
                   Integer numValue = Integer.parseInt(txtInput1.getText().toString());

                   if (numValue > 200){
                       validator1.setText(R.string.app_OutOfRange);
                       return;
                   }

                   StartValue = txtInput1.getText().toString();
               }else {
                   validator1.setText(R.string.app_NotNumber);
                   return;
               }

               if(ValidateInput(txtInput2.getText().toString())){
                   Integer numValue = Integer.parseInt(txtInput2.getText().toString());

                   if (numValue > 200){
                       validator2.setText(R.string.app_OutOfRange);
                       return;
                   }

                   FinalValue = txtInput2.getText().toString();
               }else {
                   validator2.setText(R.string.app_NotNumber);
                   return;
               }

               if(Integer.parseInt(StartValue) >= Integer.parseInt(FinalValue)){
                   validator1.setText(R.string.app_NumberInvalid);
                   return;
               }

                if (isConnected(context)) {
                    new HttpAsyncTask(jsonResponse).execute("http://172.28.11.16:58366/api/numbers?inicio=" + StartValue + "&final=" + FinalValue);
                }else {
                    Toast.makeText(context,"No connected!", Toast.LENGTH_LONG).show();
                }
            }
        });

        txtInput1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    validator1.setText("");
                }
            }
        });

        txtInput2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    validator2.setText("");
                }
            }
        });
    }

    private static String GET(String url){
        HttpURLConnection urlConnection;
        try {

            URL urlString = new URL(url);
            urlConnection = (HttpURLConnection) urlString.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            if(reader != null) {
                return convertInputStreamToString(in);
            } else {
                return "Did not work!";}

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
            return e.getLocalizedMessage();
        }

    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line;
        StringBuilder result = new StringBuilder();
        while((line = bufferedReader.readLine()) != null)
            result.append(line);
        inputStream.close();

        return result.toString();

    }

    private boolean isConnected(@NonNull Context context){
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }


    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        String Response;

        HttpAsyncTask(String Response) {
            this.Response = Response;
        }

        @Override
        protected String doInBackground(String... urls) {
            return GET(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            parseJson(result);
        }
    }

    private void parseJson(String data){

        try {
            //Log.d("Data received: ", data);
            jsonArray = new JSONArray(data);
            //Toast.makeText(context,"Data received from server", Toast.LENGTH_LONG).show();

            itemList = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {

                numero = new Numero();
                numero.numero = Integer.parseInt(jsonArray.getJSONObject(i).getString("numero"));

                itemList.add(numero);
            }

            Toast.makeText(MainActivity.this, "Numeros primos encontrados: " + itemList.size(), Toast.LENGTH_LONG).show();


            if (itemList.size() >= 100){

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle(R.string.app_title);
                alert.setMessage("Total de numeros primos encontrados: " + itemList.size());
                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
                alert.create();
                alert.show();


            } else {

                GoToList = new Intent(context, ListActivity.class);
                Bundle params = new Bundle();
                params.putString("data", data);
                params.putString("StartRange", StartValue);
                params.putString("FinalRange", FinalValue);
                GoToList.putExtra("param", params);
                startActivity(GoToList);
            }

        } catch (Throwable t) {
            Log.e("Error", "Could not parse malformed data: \"" + t.getLocalizedMessage() + "\"");
        }

    }

    private boolean ValidateInput(String input){

        String regex ="^[0-9]+$";
        Matcher matcher = Pattern.compile( regex ).matcher(input);

        return matcher.find();

    }
}
