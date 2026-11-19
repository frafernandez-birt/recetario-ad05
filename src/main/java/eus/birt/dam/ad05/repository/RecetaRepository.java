package eus.birt.dam.ad05.repository;

import eus.birt.dam.ad05.domain.Receta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecetaRepository extends JpaRepository<Receta, Long> {
}