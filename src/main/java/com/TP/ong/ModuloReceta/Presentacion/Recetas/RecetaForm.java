package com.TP.ong.ModuloReceta.Presentacion.Recetas;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class RecetaForm {

    private Long id; // solo para cuando lo quiero editar

    private String nombre;

    private String descripcion;

    private List<IngredienteForm> ingredientes = new ArrayList<>();

    @Data
    public static class IngredienteForm {
        private Long ingredienteId;
        private double cantidad;
        private int calorias;
    }
}