/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.TP.ong.ModuloFamilia.Servicios;
import com.TP.ong.ModuloFamilia.Presentacion.Familias.FamiliaBuscarForm;
import com.TP.ong.ModuloFamilia.AccesoDAO.IFamiliaRepo;
import com.TP.ong.ModuloFamilia.Entidades.Familias;
import com.TP.ong.ModuloFamilia.Excepciones.Excepcion;
import com.TP.ong.ModuloFamilia.Presentacion.Familias.FamiliaBuscarForm;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamiliaServiceImpl implements FamiliaService {

    @Autowired
    private IFamiliaRepo familiaRepo;

    @Override
    public List<Familias> getAll() {
        return familiaRepo.findAll();
    }

    @Override
    public Familias getById(Long id) {
        return familiaRepo.findById(id).orElse(null);
    }

    @Override
    public List<Familias> filter(FamiliaBuscarForm filter) throws Excepcion {
        // Aquí vas a definir tu lógica de filtro, por ejemplo:
        // buscar por nombreFamilia o persona.id, etc.
        // Por ejemplo, podrías usar los métodos custom de IFamiliaRepo que definiste:
        if (filter.getNombreFamilia() != null && !filter.getNombreFamilia().isEmpty()) {
            return familiaRepo.findByNombreOrIdPersona(filter.getNombreFamilia(), filter.getIdPersona());
        }
        return familiaRepo.findAll();
    }

    @Override
    public void deleteById(Long id) {
        familiaRepo.deleteById(id);
    }

    @Override
    public void save(Familias familia) throws Excepcion {
        // Podés validar antes de guardar si querés.
        familiaRepo.save(familia);
    }
}
