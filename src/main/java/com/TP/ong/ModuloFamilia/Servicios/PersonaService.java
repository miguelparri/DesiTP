package com.TP.ong.ModuloFamilia.Servicios;

import java.util.List;
import com.TP.ong.ModuloFamilia.Entidades.Personas;
import com.TP.ong.ModuloFamilia.Presentacion.Personas.PersonasBuscarForm;
import com.TP.ong.ModuloFamilia.Excepciones.Excepcion;

public interface PersonaService {

    List<Personas> getAll();

    Personas getById(Long idPersona);

    List<Personas> filter(PersonasBuscarForm filter) throws Excepcion;

    void save(Personas persona) throws Excepcion;

    void deleteById(Long id);

    Long generarNumeroFamilia();

    List<Personas> findByNumeroFamilia(Long numeroFamilia);

    
    void deleteByNumeroFamilia(Long numeroFamilia);
}
