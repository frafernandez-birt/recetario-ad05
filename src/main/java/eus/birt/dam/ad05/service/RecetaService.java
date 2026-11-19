package eus.birt.dam.ad05.service;

import eus.birt.dam.ad05.domain.Receta;
import eus.birt.dam.ad05.repository.RecetaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecetaService {

    private final RecetaRepository recetaRepository;

    // Inyección por constructor (como pide el enunciado)
    public RecetaService(RecetaRepository recetaRepository) {
        this.recetaRepository = recetaRepository;
    }

    public List<Receta> findAll() {
        return recetaRepository.findAll();
    }

    public Receta findById(Long id) {
        // Si no la encuentra, devuelve null..
        return recetaRepository.findById(id).orElse(null);
    }

    public void save(Receta receta) {
        recetaRepository.save(receta);
    }
    
    public void deleteById(Long id) {
        // La cascada JPA (CascadeType.ALL en la entidad Receta) 
        // se encargará de borrar los pasos asociados automáticamente.
        recetaRepository.deleteById(id);
    }
}