package com.example.parcial3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edtCorreo,edtClave;
    Button btnIngresar;

    Usuarios usuarios;

    Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resources = getResources();

        edtCorreo= findViewById(R.id.edtCorreo);
        edtClave=findViewById(R.id.edtClave);

        btnIngresar=findViewById(R.id.btnIngresar);

        usuarios = new Usuarios();
    }

    public void ingresar(View v){
        String correo = edtCorreo.getText().toString().trim();
        String clave = edtClave.getText().toString();
        boolean login = false;
        if(TextUtils.isEmpty(correo)){
            edtCorreo.setError(resources.getString(R.string.valorRequerido));
            edtCorreo.requestFocus();
        }else if(TextUtils.isEmpty(clave)){
            edtClave.setError(resources.getString(R.string.valorRequerido));
            edtClave.requestFocus();
        }else {
            for(int i = 0; i< Usuarios.correo.size(); i++){
                if(correo.equals(Usuarios.correo.get(i)) && clave.equals(Usuarios.clave.get(i))){
                    login = true;
                    break;
                }
            }
            if(login){
                edtCorreo.setText("");
                edtClave.setText("");
                Intent menu = new Intent(this, Menu.class);
                startActivity(menu);
            }else {
                Toast.makeText(getApplicationContext(),resources.getString(R.string.msj_login), Toast.LENGTH_LONG).show();
            }
        }
    }
}