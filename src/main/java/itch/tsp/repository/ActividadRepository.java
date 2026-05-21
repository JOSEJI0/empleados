package itch.tsp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import itch.tsp.model.Actividad;

public interface ActividadRepository extends JpaRepository<Actividad, Integer> {
    List<Actividad> findByActivo(Boolean activo); // <-- Ya lo tienes
}