package com.example.parcial3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Buscar extends AppCompatActivity {

    EditText edtBuscarCorroe;
    Button btnBuscarCorre,btnCancelarBus;

    Bundle dato;

    Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        resources = getResources();
        edtBuscarCorroe =findViewById(R.id.edtBuscarCorreo);

        btnBuscarCorre= findViewById(R.id.btnBuscarCorre);
        btnCancelarBus= findViewById(R.id.btnCancelarBus);

        dato = getIntent().getExtras();

    }

    public void buscar(View v){
        String correo = edtBuscarCorroe.getText().toString().trim();
        boolean elCorreo = false;
        int posicion = 0;
        if(correo.isEmpty()){
            edtBuscarCorroe.setError("Campo vacío");
            edtBuscarCorroe.requestFocus();
        }else {
            for(int i = 0; i<Usuarios.correo.size(); i++){
                if(correo.equals(Usuarios.correo.get(i))){
                    posicion = i;
                    elCorreo = true;
                    break;
                }
            }
            if(elCorreo){
                String accion = dato.getString("accion");
                if(accion.equals("editar")){
                    Intent editar = new Intent(this, Nuevo.class);
                    editar.putExtra("posicion", posicion);
                    editar.putExtra("accion" , accion);
                    startActivity(editar);
                }else {
                    Intent mostrar = new Intent(this,MostrarConfirmarDatos.class);
                    mostrar.putExtra("posicion", posicion);
                    mostrar.putExtra("accion",accion);
                    startActivity(mostrar);
                }

            }else {
                Toast.makeText(this, resources.getString(R.string.msj_CorreoNO),Toast.LENGTH_LONG).show();
            }
        }
    }

    public void cancelar(View v){
        finish();
    }
}