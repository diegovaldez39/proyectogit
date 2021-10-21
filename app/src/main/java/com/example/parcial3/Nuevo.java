package com.example.parcial3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Nuevo extends AppCompatActivity {

    EditText edtNombreNue,edtApellidoNue,edtCorreoNue,edtClaveNue,edtConfirmarCla;
    RadioButton rdbUsuario,rdbAsistente,rdbAdministrador;
    Button btnGuardarNue,btnCancelarNue;

    Bundle datos;
    int posicion,cantidad;
    String accion;

    Resources resources;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        edtNombreNue= findViewById(R.id.edtNombreNue);
        edtApellidoNue=findViewById(R.id.edtApellidoNue);
        edtCorreoNue= findViewById(R.id.edtCorreoNue);
        edtClaveNue=findViewById(R.id.edtClaveNue);
        edtConfirmarCla=findViewById(R.id.edtConfirmarCla);

        rdbUsuario=findViewById(R.id.rdbUsuario);
        rdbAsistente=findViewById(R.id.rdbAsistente);
        rdbAdministrador=findViewById(R.id.rdbAdministrador);

        btnGuardarNue=findViewById(R.id.btnGuardarNue);
        btnCancelarNue=findViewById(R.id.btnGuardarNue);

        cantidad = Usuarios.correo.size();

        resources = getResources();

        datos = getIntent().getExtras();
        accion = datos.getString("accion");
        if(!accion.equals("nuevo")){
            posicion = datos.getInt("posicion");
            String tipo = Usuarios.tipoCargo.get(posicion).toString().trim();

            edtNombreNue.setText(Usuarios.nombres.get(posicion).toString());
            edtApellidoNue.setText(Usuarios.apellidos.get(posicion).toString());
            edtCorreoNue.setText(Usuarios.correo.get(posicion).toString().trim());
            edtClaveNue.setText(Usuarios.clave.get(posicion).toString());
            edtConfirmarCla.setText(Usuarios.clave.get(posicion).toString());

            if(tipo.equals("1"))
                rdbUsuario.setChecked(true);
            else if(tipo.equals("2"))
                rdbAsistente.setChecked(true);
            else
                rdbAdministrador.setChecked(true);
        }
    }
    public void guardar(View v) {
        String nombres = edtNombreNue.getText().toString();
        String apellidos = edtApellidoNue.getText().toString();
        String correo = edtCorreoNue.getText().toString().trim();
        String clave = edtClaveNue.getText().toString();
        String confirmarClave = edtConfirmarCla.getText().toString();
        if (accion.equals("nuevo")) {
            int nivel = 4;

            if(TextUtils.isEmpty(nombres)){
                edtNombreNue.setError(resources.getString(R.string.valorRequerido));
                edtNombreNue.requestFocus();
            }else if(TextUtils.isEmpty(apellidos)){
                edtApellidoNue.setError(resources.getString(R.string.valorRequerido));
                edtApellidoNue.requestFocus();
            }else if(TextUtils.isEmpty(correo)){
                edtCorreoNue.setError(resources.getString(R.string.valorRequerido));
                edtCorreoNue.requestFocus();
            }else if(TextUtils.isEmpty(clave)){
                edtClaveNue.setError(resources.getString(R.string.valorRequerido));
                edtClaveNue.requestFocus();
            }else if(TextUtils.isEmpty(confirmarClave)){
                edtConfirmarCla.setError(resources.getString(R.string.valorRequerido));
                edtConfirmarCla.requestFocus();
            }else if(!clave.equals(confirmarClave)) {
                edtConfirmarCla.setError(resources.getString(R.string.msj_clavesNO));
                edtConfirmarCla.requestFocus();
            }
            else {
                if(rdbAdministrador.isChecked()){
                    nivel = 3;
                }
                else if(rdbAsistente.isChecked()){
                    nivel = 2;
                }
                else if(rdbUsuario.isChecked()){
                    nivel = 1;
                }
                else if(nivel==4){
                    Toast.makeText(getApplicationContext(),resources.getString(R.string.msj_seleccionar),Toast.LENGTH_LONG).show();
                }
                if(nivel < 4){
                    int longitud = cantidad+1;
                    Intent datos = new Intent(getApplicationContext(),MostrarConfirmarDatos.class);
                    datos.putExtra("nombres",nombres );
                    datos.putExtra("apellidos", apellidos);
                    datos.putExtra("correo", correo);
                    datos.putExtra("clave", clave);
                    datos.putExtra("tipo", nivel);
                    datos.putExtra("posicion",longitud);
                    datos.putExtra("accion", "guardar");
                    startActivity(datos);
                }
            }


        } else {
            Intent confirmar = new Intent(this,MostrarConfirmarDatos.class);
            int tipo = rdbUsuario.isChecked() ? 1 : rdbAsistente.isChecked() ? 2 : rdbAdministrador.isChecked() ? 3 : -1;
            if(clave.equals(confirmarClave)){
                confirmar.putExtra("posicion", posicion);
                confirmar.putExtra("nombres",nombres);
                confirmar.putExtra("apellidos",apellidos);
                confirmar.putExtra("correo",correo);
                confirmar.putExtra("clave",clave);
                confirmar.putExtra("tipo", tipo);
                confirmar.putExtra("accion", "editar");
                startActivity(confirmar);
            }else {
                Toast.makeText(getApplicationContext(),resources.getString(R.string.msj_clavesNO), Toast.LENGTH_LONG).show();
            }
        }
    }


    public void cancelar(View v){
        Intent menu = new Intent(this,Menu.class);
        menu.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(menu);
        finish();
    }
}