package com.darkmarksdoe.basketboard.Vistas.Actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.darkmarksdoe.basketboard.R;
import com.darkmarksdoe.basketboard.Vistas.Fragmentos.Equipo1Fragment;
import com.darkmarksdoe.basketboard.Vistas.Fragmentos.Etapa1;
import com.darkmarksdoe.basketboard.Vistas.Fragmentos.Etapa2;
import com.darkmarksdoe.basketboard.Vistas.Fragmentos.Etapa3;
import com.darkmarksdoe.basketboard.Vistas.Fragmentos.Etapa4;
import com.darkmarksdoe.basketboard.Vistas.Fragmentos.PartidosFragment;
import com.darkmarksdoe.basketboard.dummy.DummyContent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.spark.submitbutton.SubmitButton;

public class CrearPartidoActivity extends AppCompatActivity implements Etapa1.OnFragmentInteractionListener, Etapa2.OnFragmentInteractionListener, Etapa3.OnFragmentInteractionListener, Etapa4.OnFragmentInteractionListener, Equipo1Fragment.OnListFragmentInteractionListener {
    public int fragmento = 1;
    private FloatingActionButton btnSiguiente,btnAnterior;

    //Datos del Partido
    String Equipo1, Equipo2, Jornada, Sede;
    String CrewChief, Umpire1, Umpire2, Scorer, AssistantScorer, Timer, ShotClockOperator;
    SubmitButton btn_comenzarpartido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_partido);
        btn_comenzarpartido = findViewById(R.id.btn_comenzarpartido);
        btn_comenzarpartido.setVisibility(View.GONE);
        cargarFragmento1();
        cargarBoton();
        cargarMetodos();
    }

    private void cargarMetodos() {
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmento += 1;
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
        btn_comenzarpartido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmento = 5;
                comparar();
            }
        });
    }

    private void cargarBoton() {
        btnSiguiente = findViewById(R.id.btnSiguiente);
        btnAnterior = findViewById(R.id.btnAnterior);

    }

    private void asignarValores(){

    }

    public void comparar() {
        switch (fragmento){
            case 1:
                Etapa1 etapa1=new Etapa1();
                getSupportFragmentManager().beginTransaction().replace(R.id.layoutCreacion,etapa1).commit();
                Equipo1 = etapa1.Eq1;
                Equipo2 = etapa1.Eq2;
                Sede = etapa1.Sede;
                btn_comenzarpartido.setVisibility(View.GONE);
                break;
            case 2:
                Etapa2 etapa2=new Etapa2();
                getSupportFragmentManager().beginTransaction().replace(R.id.layoutCreacion,etapa2).commit();
                CrewChief = etapa2.CrewChief;
                Umpire1 = etapa2.Umpire1;
                Umpire2 = etapa2.Umpire2;
                Scorer = etapa2.Scorer;
                AssistantScorer = etapa2.Assistant_Scorer;
                Timer = etapa2.Timer;
                ShotClockOperator = etapa2.Shot_Clock_Operator;
                btn_comenzarpartido.setVisibility(View.GONE);
                break;
            case 3:
                Etapa3 etapa3 =new Etapa3();
                getSupportFragmentManager().beginTransaction().replace(R.id.layoutCreacion,etapa3).commit();
                btnSiguiente.show();
                btn_comenzarpartido.setVisibility(View.GONE);
                break;
            case 4:
                Etapa4 etapa4=new Etapa4();
                getSupportFragmentManager().beginTransaction().replace(R.id.layoutCreacion,etapa4).commit();
                btnSiguiente.hide();
                btn_comenzarpartido.setVisibility(View.VISIBLE);
                break;
            case 5:
                try{
                    Intent intentarPartido = new Intent(this,PartidoActualActivity.class);
                    startActivity(intentarPartido);
                }catch(Exception e){
                    e.printStackTrace();
                }
                break;

        }

    }


    public void cargarFragmento1() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.layoutCreacion,new Etapa1())
                .commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
