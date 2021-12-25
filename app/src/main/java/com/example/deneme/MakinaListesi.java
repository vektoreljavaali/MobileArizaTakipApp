package com.example.deneme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.deneme.entity.Makine;
import com.example.deneme.ui.arizaac.ArizaAcFragment;
import com.example.deneme.utility.StaticValues;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MakinaListesi extends AppCompatActivity {

    private ListView makinelistesi;
    List<String> mkl = new ArrayList<>();
    List<Makine> makineList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makina_listesi);

        GetAllUsers();
        makinelistesi = findViewById(R.id.listmakine);

        ArrayAdapter<String> veriAdaptoru=new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, android.R.id.text1, mkl);

        makinelistesi.setAdapter(veriAdaptoru);
        makinelistesi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clickItem(position);
            }
        });
    }

    private void clickItem(int position){
        GetAllById(position);

     }

    private void GetAllUsers(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://"+StaticValues.URL+":9090/makine/findbyid?id=0";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {

                            JSONArray array = new JSONArray(response);
                           for (int i=0;i<array.length();i++){
                               JSONObject object = array.getJSONObject(i);
                               String ad = object.getString("ad");
                               long id = object.getLong("id");
                               long parentid = object.getLong("parentid");
                               makineList.add(new Makine(id,ad,parentid));
                               mkl.add(ad);
                           }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                       islemyap();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("İSTEK SONUCU..: ", "onResponse: "+ error.toString());
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    private void GetAllById(int position){
        long id = makineList.get(position).getId();
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://"+StaticValues.URL+":9090/makine/findbyid?id="+id;

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {

                            JSONArray array = new JSONArray(response);
                            if(array.length()>0){
                                makineList.clear();
                                mkl.clear();
                                for (int i=0;i<array.length();i++){
                                    JSONObject object = array.getJSONObject(i);
                                    String ad = object.getString("ad");
                                    long id = object.getLong("id");
                                    long parentid = object.getLong("parentid");
                                    makineList.add(new Makine(id,ad,parentid));
                                    mkl.add(ad);
                                }
                                islemyap();
                            }else{
                                setMakine(position);
                            }

                        } catch (JSONException e) {
                            Log.d("TAG", "onResponse: "+e.toString());
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("İSTEK SONUCU..: ", "onResponse: "+ error.toString());
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    private void islemyap(){
        ArrayAdapter<String> veriAdaptoru=new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, android.R.id.text1, mkl);

        makinelistesi.setAdapter(veriAdaptoru);
    }

    private void setMakine(int position){
        Makine makine = makineList.get(position);
        ArizaAcFragment.makine = makine;
        this.finish();
    }

}