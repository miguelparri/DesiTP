/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package com.TP.ong.ModuloFamilia.Excepciones;

/**
 * Excepción personalizada para el módulo de Familia/Personas.
 */
public class Excepcion extends Exception {

    public Excepcion() {
        super();
    }

    public Excepcion(String mensaje) {
        super(mensaje);
    }

    public Excepcion(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
