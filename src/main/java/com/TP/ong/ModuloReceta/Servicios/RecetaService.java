package com.TP.ong.ModuloReceta.Servicios;

import com.TP.ong.ModuloReceta.Entidades.Receta;
import java.util.List;

public interface RecetaService {
    Receta crearReceta(Receta receta);
    void eliminarReceta(Long id);
    Receta modificarReceta(Long id, Receta receta);
    List<Receta> listarRecetas();
    List<Receta> filtrarPorCalorias(int min, int max);
}