package com.TP.ong.ModuloFamilia.Servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TP.ong.ModuloFamilia.AccesoDAO.IPersonaDao;
import com.TP.ong.ModuloFamilia.Entidades.Personas;
import com.TP.ong.ModuloFamilia.Presentacion.Personas.PersonasBuscarForm;
import com.TP.ong.ModuloFamilia.Excepciones.Excepcion;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private IPersonaDao personaDao;

    @Override
    public List<Personas> getAll() {
        return personaDao.findAll();
    }

    @Override
    public Personas getById(Long idPersona) {
        return personaDao.findById(idPersona).orElse(null);
    }

    @Override
    public List<Personas> filter(PersonasBuscarForm filter) throws Excepcion {
        String nombre = (filter.getNombre() != null) ? filter.getNombre() : "";
        String apellido = (filter.getApellido() != null) ? filter.getApellido() : "";
        String dni = (filter.getDni() != null) ? filter.getDni() : "";

        return personaDao.findByNombreContainingIgnoreCaseAndApellidoContainingIgnoreCaseAndDniContaining(nombre, apellido, dni);
    }

    @Override
    public void save(Personas persona) throws Excepcion {
        Personas personaExistente = personaDao.findByDni(persona.getDni());

        if (personaExistente != null) {
            // Si es nuevo o está intentando modificar el DNI a uno que ya existe en otra persona
            if (persona.getId() == null || !personaExistente.getId().equals(persona.getId())) {
                throw new Excepcion("Ya existe una persona con ese DNI.");
            }
        }

        personaDao.save(persona);
    }

    @Override
    public void deleteById(Long id) {
        personaDao.deleteById(id);
    }

    @Override
    public Long generarNumeroFamilia() {
        Long max = personaDao.findMaxNumeroFamilia();
        return (max == null) ? 1 : max + 1;
    }
}

