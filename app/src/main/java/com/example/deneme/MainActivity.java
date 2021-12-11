package com.example.deneme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtkullaniciadi;
    EditText txtsifre;
    Button btngiris;
    SharedPreferences preferences;
    CheckBox chkbenihatirla;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Uygulama ayağa kaltığı zaman xml den canlı bir sınıf oluşturuyot
        // oluşan sınıfta var olan tüm bileşenler değişken olarak tanımlanır.
        setContentView(R.layout.activity_main);
        // DİKKAT!!!!
        // xml sayfaları içinde var olan tasarım bileşenlerinin
        // java tarafında nesne olarak kullanılabilmesi çinç 
        // İlgili sayfayı set eden kodun altında neslerin oluşturulması
        // gereklidir.
        txtkullaniciadi = findViewById(R.id.txtUserName);
        txtsifre = findViewById(R.id.txtpassword);
        btngiris = findViewById(R.id.btnGiris);
        chkbenihatirla = findViewById(R.id.chkBeniHatirla);
        btngiris.setOnClickListener(v -> {
            // Lütfen listener kodlarınızı direkt olarak yazmayın. 
            // ayrı bir method oluşturrarak burada o kodu çağırın.
            this.onLogin();
        });
        this.RememberMe();
    }

    private void RememberMe(){
        preferences = this.getPreferences(Context.MODE_PRIVATE);
        String ischecked =  preferences.getString("ISCHECKED","false");
        String username =  preferences.getString("UY_USER","");
        String password =  preferences.getString("UY_PASSWORD","");
        if(ischecked.equals("true")){
            chkbenihatirla.setChecked(true);
            txtsifre.setText(password);
            txtkullaniciadi.setText(username);
        }
    }

    private void onLogin(){
        if("Admin".equals(txtkullaniciadi.getText().toString()) &&
                "123".equals(txtsifre.getText().toString())){
            preferences = this.getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = preferences.edit();
            if(chkbenihatirla.isChecked()){
                edit.putString("UY_USER",txtkullaniciadi.getText().toString());
                edit.putString("UY_PASSWORD",txtsifre.getText().toString());
                edit.putString("ISCHECKED","true");
                edit.apply();
            }else{
                edit.remove("UY_USER");
                edit.remove("UY_PASSWORD");
                edit.remove("ISCHECKED");
                edit.apply();
            }

            // Bir safyadan diğer bir sayfaya geçiş için hareketin yönünü belirtmelisininiz.
            // İntent üzerinde bir sayfadan diğer bir sayfaya veri aktarımı yapılabilir.
            Intent intent = new Intent(this,CafePage.class);
            // Açılması istenilen aktiviti başlatlır.
            startActivity(intent);
        }else{
            Toast.makeText(this, "Kullanıcı Adı ya da Şifre Hatalı"+ txtkullaniciadi.getText()
                    + " - " + txtsifre.getText(), Toast.LENGTH_SHORT).show();
        }

    }
    
}