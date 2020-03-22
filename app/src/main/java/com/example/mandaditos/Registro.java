package com.example.mandaditos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registro extends AppCompatActivity {

    Button mbtnRegister;
    EditText mNumber, mPass, mPass2;

    DatabaseReference dataUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        dataUsers = FirebaseDatabase.getInstance().getReference("users");

        mNumber = (EditText) findViewById(R.id.edtNumero);
        mPass = (EditText) findViewById(R.id.edtPassword);
        mPass2 = (EditText) findViewById(R.id.edtPass2);
        mbtnRegister = (Button) findViewById(R.id.btnRegistrar);

        mbtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifica();
            }
        });
    }

    private void verifica() {
        String number = mNumber.getText().toString().trim();
        String pass = mPass.getText().toString().trim();
        String pass2 = mPass2.getText().toString().trim();



        // Condiciones para verificar campos
        if (TextUtils.isEmpty(number)) {
            mNumber.setError("El telefono es requerido!");
            return;
        } else {
            if (TextUtils.isEmpty(pass)) {
                mPass.setError("Ingresa una contraseña!!");
                return;
            } else {
                if (pass.length() < 6) {
                    mPass.setError("Tu contraseña debe ser mayor a 6 caracteres");
                    return;
                } else {
                    if (TextUtils.isEmpty(pass2)) {
                        mPass2.setError("Este campo es requerido!!");
                        return;
                    } else {
                        if (!pass2.equals(pass)) {
                            mPass2.setError("Tu contraseña no coincide");
                            return;
                        }else{
                            String id = dataUsers.push().getKey();

                            User user = new User(number,pass,pass2);
                            dataUsers.child(number).setValue(user);

                            Toast.makeText(this,"Usuario registrado",Toast.LENGTH_LONG).show();

                            Intent i = new Intent(Registro.this,MainActivity.class);
                            startActivity(i);
                        }
                    }
                }
            }
        }
    }
}
