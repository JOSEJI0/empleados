package itch.tsp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import itch.tsp.model.ActividadEmpleado;

public interface ActividadEmpleadoRepository extends JpaRepository <ActividadEmpleado, Integer> {
    List<ActividadEmpleado> findByActivo(Boolean activo);
}