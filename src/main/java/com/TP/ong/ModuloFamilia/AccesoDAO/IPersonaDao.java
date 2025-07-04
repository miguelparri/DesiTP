

package com.TP.ong.ModuloFamilia.AccesoDAO;

import com.TP.ong.ModuloFamilia.Entidades.Personas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface IPersonaDao extends JpaRepository<Personas, Long> {

    Personas findByDni(String dni);

    List<Personas> findByNombreContainingOrApellidoContaining(String nombre, String apellido);

    @Query("SELECT p FROM Personas p WHERE p.nombre LIKE %:nombre% AND p.ocupacion = :ocupacion")
    List<Personas> buscarPorNombreYOcupacion(@Param("nombre") String nombre, @Param("ocupacion") String ocupacion);

    // Aquí agregás el método que falta:
    List<Personas> findByNombreContainingIgnoreCase(String nombre);
}
