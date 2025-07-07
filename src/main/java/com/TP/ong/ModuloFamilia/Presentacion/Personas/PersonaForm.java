/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.TP.ong.ModuloFamilia.Presentacion.Personas;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import com.TP.ong.ModuloFamilia.Entidades.Personas;

public class PersonaForm {

    private Long id;

    @NotNull
    private String dni;

    @NotNull
    private String nombre;

    @NotNull
    private String apellido;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechanacimiento;

    @NotNull
    private String ocupacion;

    @NotNull
    private String domicilio;

    private Long numeroFamilia; 

    public PersonaForm() {
        super();
    }

    public PersonaForm(Personas p) {
        this.id = p.getId();
        this.dni = p.getDni();
        this.nombre = p.getNombre();
        this.apellido = p.getApellido();
        this.fechanacimiento = java.sql.Date.valueOf(p.getFechanacimiento()); 
        this.ocupacion = p.getOcupacion();
        this.domicilio = p.getDomicilio();
        
    }

    public Personas toPojo() {
        Personas p = new Personas();
        p.setId(this.getId());
        p.setDni(this.getDni());
        p.setNombre(this.getNombre());
        p.setApellido(this.getApellido());
        if (this.fechanacimiento != null) {
            p.setFechanacimiento(new java.sql.Date(this.fechanacimiento.getTime()).toLocalDate());
        }
        p.setOcupacion(this.getOcupacion());
        p.setDomicilio(this.getDomicilio());
      
        return p;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Long getNumeroFamilia() {
        return numeroFamilia;
    }

    public void setNumeroFamilia(Long numeroFamilia) {
        this.numeroFamilia = numeroFamilia;
    }
}
