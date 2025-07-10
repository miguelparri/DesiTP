package com.TP.ong.ModuloReceta.AccesoDAO;

import com.TP.ong.ModuloReceta.Entidades.Receta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRecetaRepo extends JpaRepository<Receta, Long> {
    boolean existsByNombre(String nombre);
}