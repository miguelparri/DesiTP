package com.TP.ong.ModuloReceta.AccesoDAO;

import com.TP.ong.ModuloReceta.Entidades.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface IIngredienteDAO extends JpaRepository<Ingrediente, Long> {
    Optional<Ingrediente> findByNombre(String nombre);
}
