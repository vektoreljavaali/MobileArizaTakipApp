package com.example.deneme.utility;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.deneme.views.UserView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RestIslemleri {

    Context context;

    public RestIslemleri(Context context){
        this.context = context;
    }

    public List<UserView> getPersonel(){
        List<UserView> result = new ArrayList<>();

        RequestQueue queue = Volley.newRequestQueue(this.context);
        //http://localhost:9090/personel/findall
        String url ="http://"+StaticValues.URL+":9090/personel/findall";
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONArray array = new JSONArray(response);
                    if(array.length()>0){
                        for (int i=0;i<array.length();i++){
                            JSONObject jsonObject = array.getJSONObject(i);
                            result.add(
                                    new UserView(jsonObject.getLong("id"),
                                            jsonObject.getString("ad"),
                                            jsonObject.getString("soyad"))
                            );
                        }
                    }
                }catch (Exception exception){
                    Log.d("Rest İşlemleri", "onResponse: "+ exception);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("İSTEK SONUCU..: ", "onResponse: "+ error.toString());
            }
        });
    return result;
    }

}
