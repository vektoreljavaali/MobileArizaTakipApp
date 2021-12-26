package com.example.deneme.ui;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.deneme.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;


public class SayacTakipFragment extends Fragment {

    EditText txtaktif, txtkapasitif,txttoplam;
    EditText txtaktifilk, txtkapasitifilk,txttoplamilk;

    EditText txtenduktiffark,txtkapasitiffark;
    TextView txtkapasitifuyari;
    TextView txtenduktifuyari;
    Button btnkaydet;



    public SayacTakipFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_sayac_takip, container, false);
       txtaktif = view.findViewById(R.id.txtaktifilk);
       txtkapasitif = view.findViewById(R.id.txtkapasitifilk);
       txttoplam = view.findViewById(R.id.txttoplamilk);
        txtaktifilk = view.findViewById(R.id.txtaktif);
        txtkapasitifilk = view.findViewById(R.id.txtkapasitif);
        txttoplamilk = view.findViewById(R.id.txttoplam);
       txtenduktiffark = view.findViewById(R.id.txtenduktiffark);
       txtkapasitiffark = view.findViewById(R.id.txtkapasitiffark);
       txtkapasitifuyari = view.findViewById(R.id.txtkapasitifuyari);
       txtenduktifuyari = view.findViewById(R.id.txtenduktifuyari);
       btnkaydet = view.findViewById(R.id.btnkaydet);
       btnkaydet.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Islem();
           }
       });
        return view;
    }

    private void Islem(){
        double  farkaktif =  Double.parseDouble(txtaktif.getText().toString()) - Double.parseDouble(txtaktifilk.getText().toString());
        double  farkkapasitif =  Double.parseDouble(txtkapasitif.getText().toString()) - Double.parseDouble(txtkapasitifilk.getText().toString());
        double  farktoplam =  Double.parseDouble(txttoplam.getText().toString()) - Double.parseDouble(txttoplamilk.getText().toString());

        double oranenduktif = farkkapasitif / farktoplam * 100;
        double orankapasitif = farkaktif / farktoplam * 100;
        NumberFormat formatter = new DecimalFormat("#0.00");
        txtenduktiffark.setText(("%"+formatter.format(oranenduktif)));
        txtkapasitiffark.setText("%"+formatter.format(orankapasitif));

        if(oranenduktif>33){
            txtenduktifuyari.setText("Değer Limit Dışında");
            txtenduktifuyari.setTextColor(Color.RED);

        }else{
            txtenduktifuyari.setText("Limit içinde");
            txtenduktifuyari.setTextColor(Color.BLUE);
        }

        if(orankapasitif>20){
            txtkapasitifuyari.setText("Değer Limit Dışında");
            txtkapasitifuyari.setTextColor(Color.RED);

        }else{
            txtkapasitifuyari.setText("Limit içinde");
            txtkapasitifuyari.setTextColor(Color.BLUE);
        }
    }
}