/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.TP.ong.ModuloFamilia.Servicios;
import com.TP.ong.ModuloFamilia.Entidades.Familias;
import com.TP.ong.ModuloFamilia.Entidades.Familias;
import com.TP.ong.ModuloFamilia.Excepciones.Excepcion;
import com.TP.ong.ModuloFamilia.Presentacion.Familias.FamiliaBuscarForm;
import java.util.List;

public interface FamiliaService {

    List<Familias> getAll();

    Familias getById(Long id);

    List<Familias> filter(FamiliaBuscarForm filter) throws Excepcion;

    void deleteById(Long id);

    void save(Familias familia) throws Excepcion;
    
   

}
