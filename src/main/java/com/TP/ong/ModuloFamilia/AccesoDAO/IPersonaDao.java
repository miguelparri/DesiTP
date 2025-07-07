

package com.TP.ong.ModuloFamilia.AccesoDAO;

import com.TP.ong.ModuloFamilia.Entidades.Personas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface IPersonaDao extends JpaRepository<Personas, Long> {
  
     List<Personas> findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(String nombre, String apellido);

    @Query("SELECT p FROM Personas p WHERE p.nombre LIKE %:nombre% AND p.ocupacion = :ocupacion")
    List<Personas> buscarPorNombreYOcupacion(@Param("nombre") String nombre, @Param("ocupacion") String ocupacion);

    @Query("SELECT MAX(p.numeroFamilia) FROM Personas p")
    Integer findMaxNumeroFamilia();
}
