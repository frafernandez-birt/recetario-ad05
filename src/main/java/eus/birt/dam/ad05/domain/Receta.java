package eus.birt.dam.ad05.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recetas")
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 500)
    private String descripcion;

    // LADO "UNO" DE LA RELACIÓN
    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL)
    private List<Paso> pasos = new ArrayList<>();

    // Constructor vacío obligatorio para JPA
    public Receta() {
    }

    // --- GETTERS Y SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public List<Paso> getPasos() { return pasos; }
    public void setPasos(List<Paso> pasos) { this.pasos = pasos; }
}