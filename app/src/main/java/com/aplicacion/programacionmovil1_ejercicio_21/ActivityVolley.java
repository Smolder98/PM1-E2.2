package com.aplicacion.programacionmovil1_ejercicio_21;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ActivityVolley extends AppCompatActivity {

    ListView listView;
    ArrayList<String> titles = new ArrayList<>();
    ArrayAdapter adp;

    Button btnBuscar, btnTodo;
    EditText txtBuscar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);


        init();

        setListeners();
    }


    private void init(){
        listView = (ListView) findViewById(R.id.listViewVolley);

        txtBuscar = (EditText) findViewById(R.id.textViewBuscarVolley);

        btnBuscar = (Button) findViewById(R.id.btnBuscarVolley);
        btnTodo = (Button) findViewById(R.id.btnTodoVolley);
    }

    private void setListeners(){
        btnTodo.setOnClickListener(v -> obtenerUsuarios());
        btnBuscar.setOnClickListener(v -> obtenerUsuario());
    }

    private void obtenerUsuarios() {

        txtBuscar.setText(null);

        RequestQueue cola = Volley.newRequestQueue(this);
        String endpoint = "https://jsonplaceholder.typicode.com/posts";

        titles = new ArrayList<>();
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,
                endpoint,
                null,
                new com.android.volley.Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i =0; i< response.length();i++){
                            try {
                                JSONObject jsonObject = new JSONObject(response.get(i).toString());

                                titles.add(jsonObject.getString("title"));

                            }catch (Exception e){}

                        }

                        adp = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, titles);
                        listView.setAdapter(adp);
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }


        );

        cola.add(request);
    }

    private void obtenerUsuario() {

        if(txtBuscar.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Por favor ingresa un id de usuario", Toast.LENGTH_SHORT).show();
            return;
        }


        RequestQueue cola = Volley.newRequestQueue(this);

        String endpoint = "https://jsonplaceholder.typicode.com/posts/"+txtBuscar.getText().toString();


        titles = new ArrayList<>();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                endpoint,
                null,
                new com.android.volley.Response.Listener<JSONObject>(){

                    @Override
                    public void onResponse(JSONObject response) {



                        try {
                            titles.add(response.getString("title"));

                            adp = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, titles);

                            listView.setAdapter(adp);
                        }catch (Exception e){

                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        adp = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, titles);

                        listView.setAdapter(adp);
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        adp = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1);
                        listView.setAdapter(adp);
                        Toast.makeText(getApplicationContext(), "Error: "+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }


        );

        cola.add(request);
    }
}