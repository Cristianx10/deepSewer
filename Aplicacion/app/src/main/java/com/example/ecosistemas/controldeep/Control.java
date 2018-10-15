package com.example.ecosistemas.controldeep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Comunicar.Cliente;
import Comunicar.Mensaje;
import Comunicar.Registro;

public class Control extends AppCompatActivity implements View.OnTouchListener{

    Cliente cliente;
    String nombre;
    Mensaje mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        mensaje = new Mensaje();

        Bundle datos = getIntent().getExtras();
        Registro registro = (Registro) datos.get("registro");

        this.nombre = registro.name;
        String ip = registro.ip;
        int puerto = registro.puerto;

        cliente = new Cliente(ip, puerto);
        cliente.start();

        Button up = findViewById(R.id.up);
        Button down = findViewById(R.id.down);
        Button left = findViewById(R.id.left);
        Button right = findViewById(R.id.right);
        Button jump = findViewById(R.id.jump);
        Button start = findViewById(R.id.start);


        up.setOnTouchListener(this);
        down.setOnTouchListener(this);
        left.setOnTouchListener(this);
        right.setOnTouchListener(this);
        jump.setOnTouchListener(this);
        start.setOnTouchListener(this);


    }

    public void action(View v){
        mensaje.NAME = this.nombre;
        switch (v.getId()){
            case R.id.up:
                mensaje.UP = true;
                break;
            case R.id.down:
                mensaje.DOWN = true;
                break;
            case R.id.left:
                mensaje.LEFT = true;
                break;
            case R.id.right:
                mensaje.RIGHT = true;
                break;
            case R.id.jump:
                mensaje.ATACK = true;
                break;
            case R.id.start:
                mensaje.START = true;
                break;
        }
        cliente.enviar(mensaje);
    }

    public void actionReleased(View v){
        switch (v.getId()){
            case R.id.up:
                mensaje.UP = false;
                break;
            case R.id.down:
                mensaje.DOWN = false;
                break;
            case R.id.left:
                mensaje.LEFT = false;
                break;
            case R.id.right:
                mensaje.RIGHT = false;
                break;
            case R.id.jump:
                mensaje.ATACK = false;
                break;
            case R.id.start:
                mensaje.START = false;
                break;

        }
        cliente.enviar(mensaje);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if(event.getAction() == MotionEvent.ACTION_DOWN){
            action(v);
        }else if(event.getAction() == MotionEvent.ACTION_UP){
            actionReleased(v);
        }

        return false;
    }
}
