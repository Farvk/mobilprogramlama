package com.example.kitkir;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddBookActivity extends AppCompatActivity {

    private EditText editTextKitapIsmi, editTextKitapYazari, editTextKitapOzet;
    private ImageView imgKitapResim;
    private String kitapIsmi, kitapYazari, kitapOzeti;
    private int imgIzinAlmaKodu=0,imgIzinVerildiKodu=1    ;

    private void init(){
        editTextKitapIsmi = (EditText) findViewById(R.id.add_book_activity_editTextKitapIsmi);
        editTextKitapOzet = (EditText) findViewById(R.id.add_book_activity_editTextKitapOzeti);
        editTextKitapYazari = (EditText) findViewById(R.id.add_book_activity_editTextKitapYazari);
        imgKitapResim = (ImageView) findViewById(R.id.add_book_activity_ImageViewKitapResmi);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        init();
    }
    public void kitapKaydet (View v) {
        kitapIsmi = editTextKitapIsmi.getText().toString();
        kitapYazari = editTextKitapYazari.getText().toString();
        kitapOzeti = editTextKitapOzet.getText().toString();

        if (!TextUtils.isEmpty(kitapIsmi)) {
            if (!TextUtils.isEmpty(kitapYazari)) {
                if (!TextUtils.isEmpty(kitapOzeti)) {
                    //Kaydet
                } else
                    showToast("Kitap özeti boş olamaz ");
            } else
                showToast("Kitap yazarı boş olamaz ");
        } else
            showToast("Kitap ismi boş olamaz ");


    }
    private void  showToast(String mesaj){
        Toast.makeText(getApplicationContext(),mesaj, Toast.LENGTH_SHORT).show();

    }

    public void resimSec(View v){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},imgIzinAlmaKodu);
            }else{
             Intent resimiAl = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
             startActivityForResult(resimiAl, imgIzinVerildiKodu);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == imgIzinAlmaKodu){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent resimiAl = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(resimiAl, imgIzinVerildiKodu);
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}