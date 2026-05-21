package itch.tsp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import itch.tsp.model.Proyecto;

public interface ProyectoRepository extends JpaRepository <Proyecto, Integer> {
    List<Proyecto> findByActivo(Boolean activo);
}