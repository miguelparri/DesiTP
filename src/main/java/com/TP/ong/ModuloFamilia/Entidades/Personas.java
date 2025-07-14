package com.TP.ong.ModuloFamilia.Entidades;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Personas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dni;
    private String nombre;
    private String apellido;
    private LocalDate fechanacimiento;
    private String ocupacion;
    private String domicilio;
    private Long numeroFamilia;
    private String nombreFamilia;

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

    public LocalDate getFechanacimiento() {
        return fechanacimiento;
    }
    public void setFechanacimiento(LocalDate fechanacimiento) {
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
}
