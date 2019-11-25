package com.darkmarksdoe.basketboard.Vistas.Actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import com.darkmarksdoe.basketboard.R;
import com.darkmarksdoe.basketboard.Vistas.Fragmentos.Etapa1;
import com.darkmarksdoe.basketboard.Vistas.Fragmentos.PartidosFragment;

public class CrearPartidoActivity extends AppCompatActivity implements Etapa1.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_partido);
        cargarFragmento1();
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
