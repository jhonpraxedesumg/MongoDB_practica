package org.example;

import org.example.clases.ClsUsuarios;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        ClsUsuarios u = new ClsUsuarios();
        u.conexion();
        u.crearUsuario();
        u.leerUsuario();
    }
}