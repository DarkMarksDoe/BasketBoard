package com.darkmarksdoe.basketboard.Vistas.Actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.darkmarksdoe.basketboard.R;
import com.darkmarksdoe.basketboard.Vistas.Fragmentos.Etapa2;
import com.darkmarksdoe.basketboard.Vistas.Fragmentos.PartidosFragment;
import com.darkmarksdoe.basketboard.dummy.DummyContent;
import com.spark.submitbutton.SubmitButton;

public class PrincipalActivity extends AppCompatActivity implements PartidosFragment.OnListFragmentInteractionListener{
    private SubmitButton btn_nuevo_partido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        cargarFragmentoListas(savedInstanceState);

        cargarBoton();
        cargarEventos();
    }

    private void cargarEventos() {
        btn_nuevo_partido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intento = new Intent( PrincipalActivity.this, CrearPartidoActivity.class);
                    startActivity(intento);

                }catch (Exception e){
                    Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void cargarBoton() {
        btn_nuevo_partido = findViewById(R.id.btn_nuevo_partido);
    }


    private void cargarFragmentoListas(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            try {
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.frameLayout,new PartidosFragment())
                        .commit();
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

}
