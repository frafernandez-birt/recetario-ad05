package eus.birt.dam.ad05.service;

import eus.birt.dam.ad05.domain.Paso;
import eus.birt.dam.ad05.repository.PasoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasoService {

    private final PasoRepository pasoRepository;

    public PasoService(PasoRepository pasoRepository) {
        this.pasoRepository = pasoRepository;
    }

    public List<Paso> findAll() {
        return pasoRepository.findAll();
    }

    public Paso findById(Long id) {
        return pasoRepository.findById(id).orElse(null);
    }

    public void save(Paso paso) {
        pasoRepository.save(paso);
    }
    
    public void deleteById(Long id) {
        pasoRepository.deleteById(id);
    }
}