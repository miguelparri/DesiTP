package com.TP.ong.ModuloReceta.Entidades;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "DetalleReceta")
@Data
public class DetalleReceta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "receta_id")
    private Receta receta;

    @ManyToOne
    @JoinColumn(name = "ingrediente_id")
    private Ingrediente ingrediente;

    private double cantidad; // en kg
    private int calorias;    // enteros positivos
}