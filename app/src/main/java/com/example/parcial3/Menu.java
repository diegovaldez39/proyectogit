package com.example.parcial3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Menu extends AppCompatActivity {

    Button btnNuevoMenu,btnBuscarMenu,btnEditarMenu,btnSalirMenu,btnEliminarMenu,btnListadoMenu;

    Resources resources;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        resources = getResources();

        Button btnNuevoMenu,btnBuscarMenu,btnEditarMenu,btnSalirMenu,btnEliminarMenu,btnListadoMenu;

        btnNuevoMenu= findViewById(R.id.btnNuevoMenu);
        btnBuscarMenu= findViewById(R.id.btnBuscarMenu);
        btnEditarMenu= findViewById(R.id.btnEditarMenu);
        btnSalirMenu= findViewById(R.id.btnSalirMenu);
    }
    public void nuevo(View v){
        Intent editar = new Intent(this, Nuevo.class);
        editar.putExtra("accion", "nuevo");
        startActivity(editar);
    }

    public void buscar(View v){
        Intent buscar = new Intent(this,Buscar.class);
        buscar.putExtra("accion","buscar");
        startActivity(buscar);
    }
    public void eliminar(View v){
        Intent buscar = new Intent(this,Buscar.class);
        buscar.putExtra("accion","eliminar");
        startActivity(buscar);
    }
    public void editar(View v){
        Intent buscar = new Intent(this,Buscar.class);
        buscar.putExtra("accion","editar");
        startActivity(buscar);
    }

    public void salir(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(Menu.this,R.style.Base_Theme_MaterialComponents_Dialog_Bridge);
        builder.setTitle(resources.getString(R.string.alerta_salir_titulo));
        builder.setMessage(resources.getString(R.string.alerta_salir_msj))
                .setPositiveButton(resources.getString(R.string.alerta_SI), new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                        Toast.makeText(getApplicationContext(),resources.getString(R.string.msj_saliendo),Toast.LENGTH_SHORT).show();
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
}