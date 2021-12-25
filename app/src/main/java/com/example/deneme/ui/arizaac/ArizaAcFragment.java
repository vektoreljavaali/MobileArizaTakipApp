package com.example.deneme.ui.arizaac;

import static androidx.core.content.ContextCompat.getSystemService;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.deneme.MakinaListesi;
import com.example.deneme.R;
import com.example.deneme.entity.Makine;
import com.example.deneme.ui.anasayfa.AnaSayfaFragment;
import com.example.deneme.utility.RestIslemleri;
import com.example.deneme.utility.StaticValues;
import com.example.deneme.views.UserView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ArizaAcFragment extends Fragment {


    private ArizaAcViewModel mViewModel;
    private Spinner personelListesi;
    private Spinner oncelik;
    private Spinner bildirimturu;
    private EditText txttarih;
    private EditText txtsaat;
    private EditText txtarizatanimi;
    private ImageView btnimg;
    private TextView lblmakine;
    private Button btnarizabildir;
    public static Makine makine = new Makine();
    List<String> plist = new ArrayList<String>();
    List<UserView> result = new ArrayList<>();

    public static ArizaAcFragment newInstance() {
        return new ArizaAcFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.ariza_ac_fragment, container, false);
        personelListesi = view.findViewById(R.id.personelist);
        oncelik = view.findViewById(R.id.oncelik);
        bildirimturu = view.findViewById(R.id.bildirimturu);
        txttarih = view.findViewById(R.id.txttarih);
        txtsaat = view.findViewById(R.id.txtsaat);
        btnimg = view.findViewById(R.id.btnimagesearch);
        lblmakine = view.findViewById(R.id.lblmakinasec);
        btnarizabildir = view.findViewById(R.id.btnarizabildir);
        txtarizatanimi = view.findViewById(R.id.txtarizatanimi);
        getPersonel();
        view.getViewTreeObserver().addOnWindowFocusChangeListener(hasFocus -> onFocus());
        getParentFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if (getView()!=null){
                    getView().setFocusableInTouchMode(true);
                    getView().requestFocus();
                    sayfaYenilendi();
                }

            }
        });
        btnimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectMakina();
            }
        });

        List<String> blist = new ArrayList<String>();
        blist.add("İş Talebi");
        blist.add("Arıza Bildirimi");
        blist.add("Faaliyet Bildirimi");
        ArrayAdapter<String> adapterBildirim = new ArrayAdapter<String>(this.getContext(),
                android.R.layout.simple_spinner_item,blist);
        bildirimturu.setAdapter(adapterBildirim);
        List<String> onlist = new ArrayList<String>();
        onlist.add("Acil");
        onlist.add("Standart");
        onlist.add("İş Sırasına Göre");
        ArrayAdapter<String> adapterOncelik = new ArrayAdapter<String>(this.getContext(),
                android.R.layout.simple_spinner_item,onlist);
        oncelik.setAdapter(adapterOncelik);

        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        txttarih.setText(currentDate);
        String currentTime = new SimpleDateFormat("hh:mm", Locale.getDefault()).format(new Date());
        txtsaat.setText(currentTime);
        lblmakine.setText(makine.getAd());
        btnarizabildir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnarizabildir.setText("kayıt işleniyor....");
                btnarizabildir.setEnabled(false);
                ArizaBildir();
            }
        });
        return view;
    }

    private void ArizaBildir() {
// http://localhost:9090/arizabildirim/save?arizabildirimsekli=Ar%C4%B1za%20Bildirim&arizatanimi=Pc%20Ar%C4%B1zas%C4%B1&makinead=Plumat&makineid=1&oncelik=Acil&personelad=Hakan&personelid=9&tarih=21-12-2021%2014%3A44
        RequestQueue queue = Volley.newRequestQueue(this.getContext());
        String url ="http://"+ StaticValues.URL+":9090/arizabildirim/save?" +
                "arizabildirimsekli=" + bildirimturu.getSelectedItem()+
                "&arizatanimi=" + txtarizatanimi.getText()+
                "&makinead=" +  makine.getAd()+
                "&makineid=" + makine.getId()+
                "&oncelik=" + oncelik.getSelectedItem()+
                "&personelad=" + result.get(personelListesi.getSelectedItemPosition()).getAd()+
                "&personelid=" +result.get(personelListesi.getSelectedItemPosition()).getId()+
                "&tarih="+ txttarih.getText() + " "+ txtsaat.getText();
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                            ArizaAcildi();
                            btnarizabildir.setEnabled(true);
                            btnarizabildir.setText("ARIZA BİLDİR");

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("İSTEK SONUCU..: ", "onResponse: "+ error.toString());
            }
        });
        queue.add(request);

    }

    private void ArizaAcildi(){
        Toast.makeText(this.getContext(), "Arıza Kaydı Baraşı ile Açıldı", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ArizaAcViewModel.class);
        // TODO: Use the ViewModel
    }


    private void sayfaYenilendi(){
        Toast.makeText(this.getContext(), "SaYFA YENİLENDİ", Toast.LENGTH_SHORT).show();
    }

    private void SelectMakina(){
        Intent intent = new Intent(this.getContext(), MakinaListesi.class);
        startActivity(intent);
    }

    private void onFocus(){
        lblmakine.setText(makine.getAd());
    }

    public void getPersonel(){

        RequestQueue queue = Volley.newRequestQueue(this.getContext());
        //http://localhost:9090/personel/findall
        String url ="http://"+ StaticValues.URL+":9090/personel/findall";
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
                                personelListGetir(result);
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
        queue.add(request);
    }

    private void personelListGetir(List<UserView> userViews){

       // List<UserView> userViews = new RestIslemleri(this.getContext()).getPersonel();
        for (UserView item: userViews) {
            plist.add(item.getAd()+" "+ item.getSoyad());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(),
                android.R.layout.simple_spinner_item,plist);
        personelListesi.setAdapter(adapter);
    }

}