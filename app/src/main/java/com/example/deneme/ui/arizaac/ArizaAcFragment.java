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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deneme.MakinaListesi;
import com.example.deneme.R;
import com.example.deneme.entity.Makine;
import com.example.deneme.ui.anasayfa.AnaSayfaFragment;

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
    private ImageView btnimg;
    private TextView lblmakine;
    public static Makine makine = new Makine();
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
                AnaSayfaFragmentOpen();
            }
        });
        List<String> plist = new ArrayList<String>();
        plist.add("Ahmet");
        plist.add("Hasan");
        plist.add("Murat");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(),
                android.R.layout.simple_spinner_item,plist);
        personelListesi.setAdapter(adapter);
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
                android.R.layout.simple_spinner_item,blist);
        bildirimturu.setAdapter(adapterOncelik);

        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        txttarih.setText(currentDate);
        String currentTime = new SimpleDateFormat("hh:mm", Locale.getDefault()).format(new Date());
        txtsaat.setText(currentTime);
        lblmakine.setText(makine.getAd());
        return view;
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

     private void AnaSayfaFragmentOpen(){

    }

}