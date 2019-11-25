package com.darkmarksdoe.basketboard.Vistas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.darkmarksdoe.basketboard.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.spark.submitbutton.SubmitButton;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private SubmitButton btn_login;
    EditText txt_login_Correo, txt_login_Contra;
    private FirebaseAuth.AuthStateListener miListenerDeAutenticacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        try {
            cargarElementos();
            cargarEventos();
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }

    private void cargarEventos() {
            btn_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        firebaseLogin();
                    }catch (Exception e){
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
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
        try { mAuth.addAuthStateListener(miListenerDeAutenticacion);
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void firebaseLogin() {
        String correo = txt_login_Correo.getText().toString().trim();
        String contra = txt_login_Contra.getText().toString().trim();
        if(!TextUtils.isEmpty(correo) && !TextUtils.isEmpty(contra)){
            mAuth.signInWithEmailAndPassword(correo,contra).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Intent intent = new Intent(MainActivity.this, PrincipalActivity.class);
                        startActivity(intent);
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
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
