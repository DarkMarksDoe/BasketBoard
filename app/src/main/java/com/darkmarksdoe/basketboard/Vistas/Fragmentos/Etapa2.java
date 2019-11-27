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

public class Etapa2 extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    //Instancias propias
    private View view;
    private FirebaseDatabase database;
    MaterialSpinner et2_spinner_crew_chief,et2_spinner_umpire1,et2_spinner_umpire2,et2_spinner_scorer, et2_spinner_assistant_scorer;
    MaterialSpinner et2_spinner_timer, et2_spinner_shot_clock_operator;
    public static String CrewChief, Umpire1, Umpire2, Scorer, Assistant_Scorer, Timer, Shot_Clock_Operator;
    private final static String PATH_ARBITROS = "Arbitros";


    private final List<String> lista_crew_chief = new ArrayList<>();
    private final List<String> lista_umpire1 = new ArrayList<>();
    private final List<String> lista_umpire2 = new ArrayList<>();
    private final List<String> lista_scorer = new ArrayList<>();
    private final List<String> lista_assistent_scorer = new ArrayList<>();
    private final List<String> timer = new ArrayList<>();
    private final List<String> lista_clock_operator = new ArrayList<>();


    public Etapa2() {
        // Required empty public constructor
    }


    public static Etapa2 newInstance(String param1, String param2) {
        Etapa2 fragment = new Etapa2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_etapa2, container, false);
        database = FirebaseDatabase.getInstance();
        cargarElementos();
        agregarTituloListas();
        llenarSpiners();
        eventosSpinners();
        return view;
    }

    private void eventosSpinners() {
        et2_spinner_crew_chief.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                CrewChief = item.toString();
            }
        });
        et2_spinner_umpire1.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                Umpire1 = item.toString();
            }
        });
        et2_spinner_umpire2.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                Umpire2 = item.toString();
            }
        });
        et2_spinner_scorer.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                Scorer = item.toString();
            }
        });
        et2_spinner_assistant_scorer.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                Assistant_Scorer = item.toString();
            }
        });
        et2_spinner_timer.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                Timer = item.toString();
            }
        });
        et2_spinner_shot_clock_operator.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                Shot_Clock_Operator = item.toString();
            }
        });
    }

    private void cargarElementos() {
        et2_spinner_crew_chief = view.findViewById(R.id.et2_spinner_crew_chief);
        et2_spinner_umpire1 = view.findViewById(R.id.et2_spinner_umpire1);
        et2_spinner_umpire2 = view.findViewById(R.id.et2_spinner_umpire2);
        et2_spinner_scorer = view.findViewById(R.id.et2_spinner_scorer);
        et2_spinner_assistant_scorer = view.findViewById(R.id.et2_spinner_assistant_scorer);
        et2_spinner_timer = view.findViewById(R.id.et2_spinner_timer);
        et2_spinner_shot_clock_operator = view.findViewById(R.id.et2_spinner_shot_clock_operator);
    }


    private void agregarTituloListas() {
        lista_crew_chief.add("Crew Chief");
        lista_umpire1.add("Umpire 1");
        lista_umpire2.add("Umpire 2");
        lista_scorer.add("Scorer");
        lista_assistent_scorer.add("Assistent Scorer");
        timer.add("Timer");
        lista_clock_operator.add("Shot Clock Operator");
    }


    private void llenarSpiners() {
        //AQUI SE LLENAN LOS SPINNERS INICIALES
        //SE NECESITARÍA UN MÉTODO PARA LLENARLOS CADA QUE OCURRA UN EVENTO.
        DatabaseReference reference = database.getReference();
        reference.child(PATH_ARBITROS).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {
                    String areaName = areaSnapshot.child("Nombre").getValue(String.class);
                    lista_crew_chief.add(areaName);
                    lista_umpire1.add(areaName);
                    lista_umpire2.add(areaName);
                    lista_scorer.add(areaName);
                    lista_assistent_scorer.add(areaName);
                    timer.add(areaName);
                    lista_clock_operator.add(areaName);
                }
                ArrayAdapter<String> adapterlast = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_list_item_1, lista_clock_operator);
                adapterlast.setDropDownViewResource(android.R.layout.simple_gallery_item);
                ArrayAdapter<String> areasAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, lista_crew_chief);
                areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                et2_spinner_crew_chief.setAdapter(areasAdapter);
                areasAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, lista_umpire1);
                et2_spinner_umpire1.setAdapter(areasAdapter);
                areasAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, lista_umpire2);
                et2_spinner_umpire2.setAdapter(areasAdapter);
                areasAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, lista_scorer);
                et2_spinner_scorer.setAdapter(areasAdapter);
                areasAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, lista_assistent_scorer);
                et2_spinner_assistant_scorer.setAdapter(areasAdapter);
                areasAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, timer);
                et2_spinner_timer.setAdapter(areasAdapter);
                adapterlast = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_gallery_item, lista_clock_operator);
                et2_spinner_shot_clock_operator.setAdapter(adapterlast);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
