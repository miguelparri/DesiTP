/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.TP.ong.ModuloFamilia.DTO;

public class FamiliaDTO {
    private Long numeroFamilia;
    private String nombreFamilia;

    public FamiliaDTO(Long numeroFamilia, String nombreFamilia) {
        this.numeroFamilia = numeroFamilia;
        this.nombreFamilia = nombreFamilia;
    }

    public Long getNumeroFamilia() {
        return numeroFamilia;
    }

    public String getNombreFamilia() {
        return nombreFamilia;
    }
}