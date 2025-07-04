/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.TP.ong.ModuloFamilia.Servicios;

import java.util.List;
import com.TP.ong.ModuloFamilia.Excepciones.Excepcion;
import com.TP.ong.ModuloFamilia.Servicios.PersonaService;
import com.TP.ong.ModuloFamilia.Entidades.Personas;
import com.TP.ong.ModuloFamilia.Presentacion.Personas.PersonasBuscarForm;
import com.TP.ong.ModuloFamilia.Servicios.PersonaService;

public interface PersonaService {

    /**
     * Obtiene todas las personas.
     * @return Lista completa de personas.
     */
    List<Personas> getAll();

    /**
     * Obtiene una persona por ID.
     * @param idPersona
     * @return Persona encontrada.
     */
    Personas getById(Long idPersona);

    /**
     * Filtra personas según los datos del formulario de búsqueda.
     * @param filter formulario con criterios de búsqueda.
     * @return Lista de personas que cumplen los filtros.
     * @throws Excepcion en caso de error en la búsqueda.
     */
    List<Personas> filter(PersonasBuscarForm filter) throws Excepcion;

    /**
     * Guarda o actualiza una persona.
     * @param persona
     * @throws Excepcion en caso de error.
     */
    void save(Personas persona) throws Excepcion;

    /**
     * Elimina una persona por ID.
     * @param id
     */
    void deleteById(Long id);

}
