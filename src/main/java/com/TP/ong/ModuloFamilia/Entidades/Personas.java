
package com.TP.ong.ModuloFamilia.Entidades;
import lombok.Data;
import java.time.LocalDate;
@Data
public class Personas {
    private Long id;
    private String dni;
    private String nombre;
    private String apellido;
    private LocalDate FechaNacimiento;
    private String ocupacion;
    private String domicilio;

}
