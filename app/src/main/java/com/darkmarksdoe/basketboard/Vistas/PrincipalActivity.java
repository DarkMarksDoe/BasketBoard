package com.darkmarksdoe.basketboard.Vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.darkmarksdoe.basketboard.PartidosFragment;
import com.darkmarksdoe.basketboard.R;
import com.darkmarksdoe.basketboard.dummy.DummyContent;

public class PrincipalActivity extends AppCompatActivity implements PartidosFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        cargarFragmentoListas(savedInstanceState);
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
