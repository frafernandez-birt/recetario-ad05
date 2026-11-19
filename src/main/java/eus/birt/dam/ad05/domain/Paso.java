package eus.birt.dam.ad05.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "pasos")
public class Paso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer orden;

    @Column(nullable = false, length = 1000)
    private String instruccion;

    // LADO "MUCHOS" DE LA RELACIÓN (El dueño de la clave foránea)
    @ManyToOne
    @JoinColumn(name = "receta_id", nullable = false)
    private Receta receta;

    // Constructor vacío
    public Paso() {
    }

    // --- GETTERS Y SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getOrden() { return orden; }
    public void setOrden(Integer orden) { this.orden = orden; }

    public String getInstruccion() { return instruccion; }
    public void setInstruccion(String instruccion) { this.instruccion = instruccion; }

    public Receta getReceta() { return receta; }
    public void setReceta(Receta receta) { this.receta = receta; }
}