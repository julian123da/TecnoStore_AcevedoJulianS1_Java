package com.mycompany.aplicaciontecnostore;

import PERSISTENCIA.Conexion;
import java.sql.Connection;

public class AplicacionTecnoStore {

    public static void main(String[] args) {
        Conexion con = new Conexion();
        con.conectar();
    }
}
