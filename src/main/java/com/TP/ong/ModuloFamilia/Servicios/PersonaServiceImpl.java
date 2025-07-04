/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package com.TP.ong.ModuloFamilia.Servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TP.ong.ModuloFamilia.AccesoDAO.IPersonaDao;  // ✅ nombre correcto
import com.TP.ong.ModuloFamilia.Entidades.Personas;
import com.TP.ong.ModuloFamilia.Presentacion.Personas.PersonasBuscarForm;
import com.TP.ong.ModuloFamilia.Excepciones.Excepcion;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private IPersonaDao personaDao;  // ✅ corregido

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
        if (filter.getNombre() != null && !filter.getNombre().isEmpty()) {
            return personaDao.findByNombreContainingIgnoreCase(filter.getNombre());
        } else {
            return personaDao.findAll();
        }
    }

    @Override
    public void save(Personas persona) throws Excepcion {
        personaDao.save(persona);
    }

    @Override
    public void deleteById(Long id) {
        personaDao.deleteById(id);
    }
}
