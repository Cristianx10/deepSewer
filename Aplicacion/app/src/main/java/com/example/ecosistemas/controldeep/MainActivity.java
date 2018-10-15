package com.example.ecosistemas.controldeep;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Comunicar.Registro;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btn_verificar = findViewById(R.id.btn_verificar);

        final EditText et_ip = findViewById(R.id.et_ip);
        final EditText et_puerto = findViewById(R.id.et_puerto);
        final EditText et_nombre = findViewById(R.id.et_nombre);

        btn_verificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ip = et_ip.getText().toString();

                int puerto = 0;
                try {
                    puerto = Integer.parseInt(et_puerto.getText().toString());
                }catch(Exception e){
                    puerto = 0;
                }

                String name = et_nombre.getText().toString();

                if(puerto !=0 && !ip.equals("") && !name.equals("")){

                    Intent juego = new Intent(MainActivity.this, Control.class);

                    Registro r = new Registro(name, ip, puerto);
                    Bundle dato = new Bundle();
                    dato.putSerializable("registro", r);
                    dato.putString("ip", "30943");

                    juego.putExtras(dato);

                    startActivity(juego);
                }else{
                    Toast.makeText(MainActivity.this, "Introduce datos validos", Toast.LENGTH_SHORT).show();
                }

            }
        });


        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        et_ip.clearFocus();
        inputMethodManager.hideSoftInputFromWindow(et_ip.getWindowToken(), 0);
    }
}
