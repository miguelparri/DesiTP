package com.TP.ong.ModuloReceta.Entidades;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Ingredientes")
@Data
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private boolean activo = true;
}

