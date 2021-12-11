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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MakinaListesi extends AppCompatActivity {

    private ListView makinelistesi;
    List<String> mkl = new ArrayList<>();
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
        List<String> mkl1 = new ArrayList<>();
        mkl1.add("Casper");
        mkl1.add("LG");
        mkl1.add("Lenovo");
        mkl1.add("Asus");
        ArrayAdapter<String> veriAdaptoru=new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, android.R.id.text1, mkl1);
        makinelistesi.setAdapter(veriAdaptoru);
        Toast.makeText(this, "Bildirim..:"+ mkl.get(position), Toast.LENGTH_SHORT).show();
    }

    private void GetAllUsers(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://randomuser.me/api/?results=20";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {
                            JSONObject jlist = new JSONObject(response);
                            JSONArray array = jlist.getJSONArray("results");
                           for (int i=0;i<array.length();i++){
                               JSONObject object = array.getJSONObject(i);
                               String email = object.getString("email");
                               mkl.add(email);
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

    private void islemyap(){
        ArrayAdapter<String> veriAdaptoru=new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, android.R.id.text1, mkl);

        makinelistesi.setAdapter(veriAdaptoru);
    }
}