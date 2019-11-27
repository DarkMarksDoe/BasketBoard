package com.darkmarksdoe.basketboard.Vistas.Fragmentos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.darkmarksdoe.basketboard.R;
import com.darkmarksdoe.basketboard.dummy.Equipo;
import com.google.firebase.FirebaseException;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;


public class Etapa1 extends Fragment {
    private static final String PADRE_RUTA = "Equipos";
    private FirebaseDatabase database;

    private final List<String> nomeConsulta = new ArrayList<String>();

    public MaterialSpinner spinerEquipo1;
    public MaterialSpinner spinerEquipo2;
    public MaterialSpinner spinerJornada;
    public MaterialSpinner spinerSede;
    private View view;

    public static String Eq1, Eq2, Jornada, Sede;

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
        spinerEquipo1.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                Eq1 = item.toString();
            }
        });

        spinerEquipo2.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                Eq2 = item.toString();
            }
        });
        spinerSede.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                Sede = item.toString();
            }
        });
    }

    private void llenarSpinners() {
        DatabaseReference reference = database.getReference();
        reference.child(PADRE_RUTA).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                final List<String> areas = new ArrayList<>();

                for (DataSnapshot areaSnapshot : dataSnapshot.getChildren()) {
                    String areaName = areaSnapshot.child("Nombre").getValue(String.class);

                    areas.add(areaName);

                }
                ArrayAdapter<String> areasAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, areas);
                areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinerEquipo1.setAdapter(areasAdapter);
                areasAdapter.remove(Eq1);
                spinerEquipo2.setAdapter(areasAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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
    }

    private void cargarElementos() {
        spinerEquipo1 = view.findViewById(R.id.et1_spinner_equipo1);
        spinerEquipo2 = view.findViewById(R.id.et1_spinner_equipo2);
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
