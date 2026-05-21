package itch.tsp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import itch.tsp.model.Habilidad;

public interface HabilidadRepository extends JpaRepository <Habilidad, Integer> {
    List<Habilidad> findByActivo(Boolean activo);
}