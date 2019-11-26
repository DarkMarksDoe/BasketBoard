package com.darkmarksdoe.basketboard.Vistas.Actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.darkmarksdoe.basketboard.R;
import com.darkmarksdoe.basketboard.Vistas.Fragmentos.Etapa1;
import com.darkmarksdoe.basketboard.Vistas.Fragmentos.Etapa2;
import com.darkmarksdoe.basketboard.Vistas.Fragmentos.Etapa3;
import com.darkmarksdoe.basketboard.Vistas.Fragmentos.Etapa4;
import com.darkmarksdoe.basketboard.Vistas.Fragmentos.PartidosFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CrearPartidoActivity extends AppCompatActivity implements Etapa1.OnFragmentInteractionListener, Etapa2.OnFragmentInteractionListener, Etapa3.OnFragmentInteractionListener, Etapa4.OnFragmentInteractionListener {
    private int fragmento = 1;
    private FloatingActionButton btnSiguiente,btnAnterior;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_partido);
        cargarFragmento1();
        cargarBoton();
        cargarMetodos();
    }

    private void cargarMetodos() {
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmento+=1;
                comparar();
            }
        });
        btnAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmento-=1;
                if(fragmento == 0){
                    finish();
                }else
                comparar();
            }
        });
    }

    private void cargarBoton() {
        btnSiguiente = findViewById(R.id.btnSiguiente);
        btnAnterior = findViewById(R.id.btnAnterior);
    }

    private void comparar() {
        switch (fragmento){
            case 1:
                Etapa1 etapa1=new Etapa1();
                getSupportFragmentManager().beginTransaction().replace(R.id.layoutCreacion,etapa1).commit();
                Toast.makeText(this, "ACTIVIDAD: " + fragmento, Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Etapa2 etapa2=new Etapa2();
                getSupportFragmentManager().beginTransaction().replace(R.id.layoutCreacion,etapa2).commit();
                Toast.makeText(this, "ACTIVIDAD: " + fragmento, Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Etapa3 etapa3 =new Etapa3();
                getSupportFragmentManager().beginTransaction().replace(R.id.layoutCreacion,etapa3).commit();
                Toast.makeText(this, "ACTIVIDAD: " + fragmento, Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Etapa4 etapa4=new Etapa4();
                getSupportFragmentManager().beginTransaction().replace(R.id.layoutCreacion,etapa4).commit();
                Toast.makeText(this, "ACTIVIDAD: " + fragmento, Toast.LENGTH_SHORT).show();
                break;

        }

    }

    private void cargarFragmento1() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.layoutCreacion,new Etapa1())
                .commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
