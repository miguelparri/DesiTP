/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.TP.ong.ModuloFamilia.Presentacion.Personas;

public class PersonasBuscarForm {
    private String nombre;
    private String apellido;
    // Agregar otros filtros si necesitás

    // getters y setters
    public String getNombre() {
        return (nombre != null && !nombre.isEmpty()) ? nombre : null;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return (apellido != null && !apellido.isEmpty()) ? apellido : null;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}