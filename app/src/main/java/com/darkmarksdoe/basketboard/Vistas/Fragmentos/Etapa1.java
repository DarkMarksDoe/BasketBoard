package com.darkmarksdoe.basketboard.Vistas.Fragmentos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.darkmarksdoe.basketboard.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;


public class Etapa1 extends Fragment {
    private static final String PADRE_RUTA = "Equipos";
    private FirebaseDatabase database;

    private final List<String> nomeConsulta = new ArrayList<String>();

    public MaterialSpinner spinerPartidos;
    public MaterialSpinner spinerJornada;
    public MaterialSpinner spinerSede;
    private View view;

    public static String Partido, Jornada, Sede;

    private OnFragmentInteractionListener mListener;

    public Etapa1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_etapa1, container, false);
        database = FirebaseDatabase.getInstance();
        cargarElementos();
        nomeConsulta.add("Selecciona un equipo");
        llenarSpinners();
        eventosSpinners();
        return view;
    }

    private void eventosSpinners() {
        spinerPartidos.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                Partido = item.toString();
            }
        });

        spinerSede.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                Sede = item.toString();
            }
        });
        spinerJornada.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                Jornada = item.toString();
                spinnerPartido(position);
            }
        });
    }

    private void spinnerPartido(final int pos){
        final DatabaseReference reference = database.getReference();
        reference.child("Fecha " + pos).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final List<String> partidos = new ArrayList<>();

                for (DataSnapshot partidoSnapshot : dataSnapshot.getChildren()) {
                    String Partido = partidoSnapshot.child("").getValue(String.class);
                    partidos.add(Partido);
                }

                ArrayAdapter<String> partidosAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, partidos);
                partidosAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinerPartidos.setAdapter(partidosAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void llenarSpinners() {
        final DatabaseReference reference = database.getReference();


        reference.child(PADRE_RUTA).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final List<String> sedes = new ArrayList<>();

                for (DataSnapshot sedeSnapshot : dataSnapshot.getChildren()) {
                    String Sede = sedeSnapshot.child("Sede").getValue(String.class);
                    sedes.add(Sede);
                }

                ArrayAdapter<String> sedesAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, sedes);
                sedesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinerSede.setAdapter(sedesAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        reference.child("Jornadas").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final List<String> jornadas = new ArrayList<>();

                for (DataSnapshot jornadaSnapshot : dataSnapshot.getChildren()) {
                    String Jornadas = jornadaSnapshot.child("").getValue(String.class);
                    jornadas.add(Jornadas);
                }

                ArrayAdapter<String> jornadasAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, jornadas);
                jornadasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinerJornada.setAdapter(jornadasAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void cargarElementos() {
        spinerPartidos = view.findViewById(R.id.et1_spinner_partidos);
        spinerJornada = view.findViewById(R.id.et1_spinner_jornada);
        spinerSede = view.findViewById(R.id.et1_spinner_sede);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
