package com.TP.ong.ModuloReceta.Entidades;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Recetas")
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;

    @Column(length = 1000)
    private String descripcion;

    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleReceta> detalles;

    private boolean activa = true;

    // Getters y setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public List<DetalleReceta> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleReceta> detalles) { this.detalles = detalles; }

    public boolean isActiva() { return activa; }
    public void setActiva(boolean activa) { this.activa = activa; }
}
