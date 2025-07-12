package com.TP.ong.ModuloFamilia.Presentacion.Familias;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FamiliaBuscarForm {

    private Long id;
    
    private String dni;

    private String nombre;

    private String apellido;

    
    private Date fechanacimiento;

   
    private String ocupacion;

    
    private String domicilio;

    private Long numeroFamilia;

    private String nombreFamilia;
    private String direccion;
    private Long idPersona;

    // Getters y Setters

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

    public String getNombreFamilia() {
        return nombreFamilia;
    }

    public void setNombreFamilia(String nombreFamilia) {
        this.nombreFamilia = nombreFamilia;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }
}
