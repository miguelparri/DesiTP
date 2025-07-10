package com.TP.ong.ModuloReceta.Presentacion.Recetas;

import lombok.Data;

@Data
public class RecetaBuscarForm {

    private String nombre;

    private Integer minCalorias;

    private Integer maxCalorias;
}