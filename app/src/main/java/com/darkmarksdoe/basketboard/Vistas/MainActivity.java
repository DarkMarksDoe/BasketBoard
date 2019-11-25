package com.darkmarksdoe.basketboard.Vistas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.darkmarksdoe.basketboard.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.spark.submitbutton.SubmitButton;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private SubmitButton btn_login;
    private EditText txt_login_Correo, txt_login_Contra;
    private FirebaseAuth.AuthStateListener miListenerDeAutenticacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_main);
        cargarElementos();
        cargarEventos();
    }

    private void cargarEventos() {
            btn_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  firebaseLogin();
                }
            });
            miListenerDeAutenticacion = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    if(firebaseAuth.getCurrentUser() != null){
                        Toast.makeText(MainActivity.this,
                                "INICIO CORRECTO:\n" + firebaseAuth.getCurrentUser().getEmail().toString(),
                                Toast.LENGTH_SHORT).show();
                        mAuth.signOut();
                    }
                }
            };
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(miListenerDeAutenticacion);
    }

    private void firebaseLogin() {
        String correo = txt_login_Correo.getText().toString().trim();
        String contra = txt_login_Contra.getText().toString().trim();
        if(!TextUtils.isEmpty(correo) && !TextUtils.isEmpty(contra)){
            mAuth.signInWithEmailAndPassword(correo,contra).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                    }else{
                        if(task.isCanceled()){
                            Toast.makeText(MainActivity.this, "Inicio cancelado", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, "Correo o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

        }else{
            if(TextUtils.isEmpty(correo)){
                Toast.makeText(this, "Correo vacío", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Contraseña vacía", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void cargarElementos() {
        try {
            btn_login = findViewById(R.id.btn_login);
            txt_login_Correo = findViewById(R.id.txt_login_Correo);
            txt_login_Contra = findViewById(R.id.txt_login_Contra);

        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
