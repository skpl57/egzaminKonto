package com.example.egzaminkonto;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText emailInput, hasloInput, powhasloInput;
    private TextView komunikat;
    private TextView duzeLitery, maleLitery, znakiSpecjalne, cyfry, dlugosc;
    private Button zatwierdz, sprawdzMoc, generujHaslo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        emailInput = findViewById(R.id.emailInput);
        hasloInput = findViewById(R.id.podajHasloInput);
        powhasloInput = findViewById(R.id.powtorzHasloInput);
        komunikat = findViewById(R.id.powitanieText);

        zatwierdz = findViewById(R.id.zatwierdzButton);
        generujHaslo = findViewById(R.id.generujButton);
        sprawdzMoc = findViewById(R.id.sprawdzMocButton);

        duzeLitery = findViewById(R.id.duzeLiteryText);
        maleLitery = findViewById(R.id.maleLiteryText);
        znakiSpecjalne = findViewById(R.id.znakiSpecjalneText);
        cyfry = findViewById(R.id.cyfryText);
        dlugosc = findViewById(R.id.dlugoscText);


        zatwierdz.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sprawdzanie();
                    }
                }
        );

        generujHaslo.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }
        );

        sprawdzMoc.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sprawdzanieMocy();
                    }
                }
        );
    }
    private void sprawdzanie(){
        if(!emailInput.getText().toString().contains("@")){
            komunikat.setText("Nieprawidłowy adres e-mail!");
            return;
        }
        if(!hasloInput.getText().toString().equals(powhasloInput.getText().toString())){
            komunikat.setText("Hasła się różnią!");
            return;
        }
        komunikat.setText("Witaj " + emailInput.getText().toString());
    }


    String tablicaDuze = "ABCDEFGHIJKLMNOPRSTWVUXYZQ";
    String tablicaZnaki = "!@#$%^&*<>,.?/\\";
    String tablicaCyfry = "1234567890";
    private void sprawdzanieMocy(){
        String haslo = hasloInput.getText().toString();

        for(int i = 0; i < tablicaDuze.length(); i++){
            if(haslo.contains(tablicaDuze.substring(i, i+1))){
                duzeLitery.setTextColor(getColor(R.color.green));
                i = tablicaDuze.length();
            }
            else{
                znakiSpecjalne.setTextColor(getColor(R.color.red));
            }
        }

        for(int i = 0; i < tablicaDuze.length(); i++){
            if(haslo.contains(tablicaDuze.toLowerCase().substring(i, i+1))){
                maleLitery.setTextColor(getColor(R.color.green));
                i = tablicaDuze.length();
            }
            else{
                znakiSpecjalne.setTextColor(getColor(R.color.red));
            }
        }

        for(int i = 0; i < tablicaZnaki.length(); i++){
            if(haslo.contains(tablicaZnaki.substring(i, i+1))){
                znakiSpecjalne.setTextColor(getColor(R.color.green));
                i = tablicaZnaki.length();
            }
            else{
                znakiSpecjalne.setTextColor(getColor(R.color.red));
            }
        }

        for(int i = 0; i < tablicaCyfry.length(); i++){
            if(haslo.contains(tablicaCyfry.substring(i, i+1))){
                cyfry.setTextColor(getColor(R.color.green));
                i = tablicaCyfry.length();
            }
            else{
                znakiSpecjalne.setTextColor(getColor(R.color.red));
            }
        }

        if(haslo.length() >= 12){
            dlugosc.setTextColor(getColor(R.color.green));
        }
        else{
            dlugosc.setTextColor(getColor(R.color.red));
        }
    }
}