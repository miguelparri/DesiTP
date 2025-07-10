package com.TP.ong.ModuloReceta.AccesoDAO;

import com.TP.ong.ModuloReceta.Entidades.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IIngredienteDAO extends JpaRepository<Ingrediente, Long> {
    //Puedo agregar mas ingredientes
}