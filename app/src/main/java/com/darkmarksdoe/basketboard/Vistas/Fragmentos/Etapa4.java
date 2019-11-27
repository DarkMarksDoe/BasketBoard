package com.darkmarksdoe.basketboard.Vistas.Fragmentos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.darkmarksdoe.basketboard.R;
import com.darkmarksdoe.basketboard.Vistas.Actividades.CrearPartidoActivity;
import com.spark.submitbutton.SubmitButton;

public class Etapa4 extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private View view;
    EditText et4_edit_text_hora, et4_edit_text_fecha;



    public Etapa4() {
        // Required empty public constructor
    }

    public static Etapa4 newInstance(String param1, String param2) {
        Etapa4 fragment = new Etapa4();
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
        view = inflater.inflate(R.layout.fragment_etapa4, container, false);
        cargarElementos();
        //agregarDatos();
        return view;
    }

    private void agregarDatos() {
        String fecha = "27 | 11 | 2019";
        String hora = "12:00:00 | PM";
        et4_edit_text_fecha.setText("" + fecha);
        et4_edit_text_hora.setText(hora + "PM");
    }

    private void cargarElementos() {
        et4_edit_text_fecha = view.findViewById(R.id.et4_textview_fecha);
        et4_edit_text_hora = view.findViewById(R.id.et4_textview_hora);
    }

    // TODO: Rename method, update argument and hook method into UI event
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
