/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.TP.ong.ModuloFamilia.Entidades;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Familias")
@Data
public class Familias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreFamilia;

    private String direccion;

    @ManyToOne
    @JoinColumn(name = "persona_id")
    private Personas persona;

    @Column(unique = true)
    private Long numeroFamilia;
}
