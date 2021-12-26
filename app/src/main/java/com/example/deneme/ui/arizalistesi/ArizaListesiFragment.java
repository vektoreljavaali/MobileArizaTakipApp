package com.example.deneme.ui.arizalistesi;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.deneme.ArizaKapatActivity;
import com.example.deneme.R;
import com.example.deneme.adapters.ArizaListAdapter;
import com.example.deneme.entity.ArizaBildirim;
import com.example.deneme.entity.Makine;
import com.example.deneme.utility.StaticValues;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ArizaListesiFragment extends Fragment {

    private ArizaListesiViewModel mViewModel;
    private RecyclerView arizalist;
    private List<ArizaBildirim> arizaBildirimList = new ArrayList<>();
    public static ArizaListesiFragment newInstance() {
        return new ArizaListesiFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
     View view = inflater.inflate(R.layout.ariza_listesi_fragment, container, false);
      arizalist = view.findViewById(R.id.arizalistrcycler);
    GetAll();
        return view;
    }


    private void GetAll(){

        RequestQueue queue = Volley.newRequestQueue(this.getContext());
        String url ="http://"+ StaticValues.URL+":9090/arizabildirim/findall";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {

                            JSONArray array = new JSONArray(response);
                            if(array.length()>0){
                                arizaBildirimList.clear();
                                for (int i=0;i<array.length();i++){
                                    JSONObject object = array.getJSONObject(i);
                                    long id = object.getLong("id");
                                    long personelid = object.getLong("personelid");
                                    String personelad = object.getString("personelad");
                                    String tarih = object.getString("tarih");
                                    String arizabildirimsekli = object.getString("arizabildirimsekli");
                                    String arizatanimi = object.getString("arizatanimi");
                                    String oncelik = object.getString("oncelik");
                                    long makineid = object.getLong("makineid");
                                    String makinead = object.getString("makinead");
                                    arizaBildirimList.add(
                                            new ArizaBildirim(id,personelid,personelad,tarih,arizabildirimsekli,arizatanimi,oncelik,makineid,makinead)
                                    );

                                }
                                islemyap();
                            }else{
                                System.out.println("Boş geldi");
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

        ArizaListAdapter adapter = new ArizaListAdapter(arizaBildirimList, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArizaKapat(v.getId());
            }
        });
        arizalist.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        arizalist.setLayoutManager(linearLayoutManager);
    }

    private  void ArizaKapat(int index){
        System.out.println("gelen id...: "+index);
        Intent intent = new Intent(this.getContext(), ArizaKapatActivity.class);
        startActivity(intent);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ArizaListesiViewModel.class);
        // TODO: Use the ViewModel
    }

}