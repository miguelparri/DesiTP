package com.TP.ong.ModuloFamilia.AccesoDAO;

import com.TP.ong.ModuloFamilia.Entidades.Familias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFamiliaRepo extends JpaRepository<Familias, Long> {

    @Query("SELECT f FROM Familias f WHERE f.nombreFamilia LIKE :nombre OR f.persona.id = :idPersonaSeleccionada")
    List<Familias> findByNombreOrIdPersona(String nombre, Long idPersonaSeleccionada);

    @Query("SELECT f FROM Familias f WHERE f.nombreFamilia LIKE :nombre AND f.persona.id = :idPersonaSeleccionada")
    List<Familias> findByNombreAndIdPersona(String nombre, Long idPersonaSeleccionada);

    @Query("SELECT f FROM Familias f WHERE f.nombreFamilia LIKE :nombre AND f.persona.id = :idPersonaSeleccionada AND f.persona.id <> :idDistintoDe")
    List<Familias> findByNombreAndIdPersonaAndIdNot(String nombre, Long idPersonaSeleccionada, Long idDistintoDe);
    
    
    @Query("SELECT MAX(f.numeroFamilia) FROM Familias f")
    Integer findMaxNumeroFamilia();
}
