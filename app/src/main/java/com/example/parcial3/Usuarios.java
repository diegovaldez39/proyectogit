package com.example.parcial3;

import java.util.ArrayList;

public class Usuarios {

    static public ArrayList nombres = new ArrayList();
    static public ArrayList apellidos = new ArrayList();
    static public ArrayList correo = new ArrayList();
    static public ArrayList clave = new ArrayList();
    static public ArrayList tipoCargo = new ArrayList();

    public Usuarios(){
        int tipoCargo = 1;
        for(int i = 1; i <= 10; i++){
            nombres.add("usuario" + i);
            apellidos.add("apellido" + i);
            correo.add("usuario" + i +"@mail.utec.edu.sv");
            clave.add("123" + i);
            this.tipoCargo.add(tipoCargo);
            tipoCargo++;
            if(tipoCargo == 4)
                tipoCargo = 1;
        }
    }
}
