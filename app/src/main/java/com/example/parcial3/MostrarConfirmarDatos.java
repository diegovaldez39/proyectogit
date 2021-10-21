package com.example.parcial3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MostrarConfirmarDatos extends AppCompatActivity {

    TextView tvMostrarNombre,tvApellido,tvMostrarCorreo,tvMostrarClave,tvMostrarTipo;
    Button btnConfirmar,btnCancelar;

    Bundle datos;
    int posicion;
    String accion , tipo, nombres, apellidos, correo, clave;

    Resources resources;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_confirmar_datos);

        tvMostrarNombre= findViewById(R.id.tvMostrarNombre);
        tvApellido=  findViewById(R.id.tvApellido);
        tvMostrarCorreo= findViewById(R.id.tvMostrarCorreo);
        tvMostrarClave= findViewById(R.id.tvMostrarClave);
        tvMostrarTipo= findViewById(R.id.tvMostrarTipo);

        btnConfirmar= findViewById(R.id.btnConfirmar);
        btnCancelar= findViewById(R.id.btnCancelar);

        datos = getIntent().getExtras();
        posicion = datos.getInt("posicion");
        accion = datos.getString("accion");

        resources = getResources();

        if(accion.equals("buscar") || accion.equals("eliminar")){
            if(accion.equals("buscar")){
                btnConfirmar.setText(resources.getString(R.string.btnRegresar));
                btnCancelar.setText(resources.getString(R.string.btnIrMenu));
            }
            tipo = Usuarios.tipoCargo.get(posicion).toString().trim();

            tvMostrarNombre.setText(resources.getString(R.string.tvMostrarNombre) + " " + Usuarios.nombres.get(posicion).toString());
            tvApellido.setText(resources.getString(R.string.tvApellido) + " " + Usuarios.apellidos.get(posicion).toString());
            tvMostrarCorreo.setText(resources.getString(R.string.tvMostrarCorreo) + " " + Usuarios.correo.get(posicion).toString().trim());
            tvMostrarClave.setText(resources.getString(R.string.tvMostrarClave) + " " + Usuarios.clave.get(posicion).toString());
        }else {
            nombres = datos.getString("nombres");
            apellidos = datos.getString("apellidos");
            correo = datos.getString("correo");
            clave = datos.getString("clave");
            tipo = String.valueOf(datos.getInt("tipo"));

            tvMostrarNombre.setText(resources.getString(R.string.tvMostrarNombre) + " "+ nombres);
            tvApellido.setText(resources.getString(R.string.tvApellido) + " " + apellidos);
            tvMostrarCorreo.setText(resources.getString(R.string.tvMostrarCorreo) + " " + correo.trim());
            tvMostrarClave.setText(resources.getString(R.string.tvMostrarClave) + " " + clave);
        }

        if(tipo.equals("1"))
            tvMostrarTipo.setText(resources.getString(R.string.rdb_Usuario));
        else if(tipo.equals("2"))
            tvMostrarTipo.setText(resources.getString(R.string.rdb_Asistente));
        else
            tvMostrarTipo.setText(resources.getString(R.string.rdb_Administrador));
    }

    public void confirmar(View v){
        if(btnConfirmar.getText().equals(resources.getString(R.string.btnConfirmar))){
            if(accion.equals("eliminar")){
                AlertDialog.Builder builder = new AlertDialog.Builder(MostrarConfirmarDatos.this,R.style.Base_Theme_MaterialComponents_Dialog_Bridge);
                builder.setTitle(resources.getString(R.string.alerta_salir_titulo));
                builder.setMessage(resources.getString(R.string.alerta_eliminar_msj))
                        .setPositiveButton(resources.getString(R.string.alerta_SI), new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Usuarios.nombres.remove(posicion);
                                Usuarios.apellidos.remove(posicion);
                                Usuarios.correo.remove(posicion);
                                Usuarios.clave.remove(posicion);
                                Usuarios.tipoCargo.remove(posicion);
                                Toast.makeText(getApplicationContext(),resources.getString(R.string.msj_datos_eliminados),Toast.LENGTH_SHORT).show();
                                Intent menu = new Intent(getApplicationContext(),Menu.class);
                                menu.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(menu);
                                finish();
                            }
                        })
                        .setNegativeButton(resources.getString(R.string.alerta_NO), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(),resources.getString(R.string.msj_cancelado),Toast.LENGTH_SHORT).show();
                                dialogInterface.dismiss();
                            }
                        }).show();
            }else if(accion.equals("editar")){
                AlertDialog.Builder builder = new AlertDialog.Builder(MostrarConfirmarDatos.this,R.style.Base_Theme_MaterialComponents_Dialog_Bridge);
                builder.setTitle(resources.getString(R.string.alerta_salir_titulo));
                builder.setMessage(resources.getString(R.string.alerta_modificar_msj))
                        .setPositiveButton(resources.getString(R.string.alerta_SI), new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Usuarios.correo.set(posicion,correo.toString().trim());
                                Usuarios.nombres.set(posicion,nombres.toString().trim());
                                Usuarios.apellidos.set(posicion,apellidos.toString().trim());
                                Usuarios.clave.set(posicion,clave.toString().trim());
                                Usuarios.tipoCargo.set(posicion, tipo);

                                Intent menu = new Intent(getApplicationContext(), Menu.class);
                                menu.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(menu);
                                finish();
                                Toast.makeText(getApplicationContext(),resources.getString(R.string.msj_datos_modif),Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton(resources.getString(R.string.alerta_NO), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(),resources.getString(R.string.msj_cancelado),Toast.LENGTH_SHORT).show();
                                dialogInterface.dismiss();
                            }
                        }).show();
            }else if(accion.equals("guardar")){
                AlertDialog.Builder builder = new AlertDialog.Builder(MostrarConfirmarDatos.this,R.style.Base_Theme_MaterialComponents_Dialog_Bridge);
                builder.setTitle(resources.getString(R.string.alerta_salir_titulo));
                builder.setMessage(resources.getString(R.string.alerta_guardar_msj))
                        .setPositiveButton(resources.getString(R.string.alerta_SI), new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Usuarios.correo.add(correo.toString().trim());
                                Usuarios.clave.add(clave);
                                Usuarios.apellidos.add(apellidos);
                                Usuarios.nombres.add(nombres);
                                Usuarios.tipoCargo.add(tipo);

                                Toast.makeText(getApplicationContext(),resources.getString(R.string.msj_datos_almacenados),Toast.LENGTH_LONG).show();

                                Intent menu = new Intent(getApplicationContext(),Menu.class);
                                menu.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(menu);
                                finish();
                            }
                        })
                        .setNegativeButton(resources.getString(R.string.alerta_NO), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(),resources.getString(R.string.msj_cancelado),Toast.LENGTH_SHORT).show();
                                dialogInterface.dismiss();
                            }
                        }).show();
            }
        }else{
            finish();
        }


    }
    public void cancelar(View v){
        if(btnCancelar.getText().equals(resources.getString(R.string.btnIrMenu))){
            Intent menu = new Intent(this,Menu.class);
            menu.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(menu);
        }
        finish();
    }
}