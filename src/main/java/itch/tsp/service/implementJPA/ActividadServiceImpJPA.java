package itch.tsp.service.implementJPA;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import itch.tsp.model.Actividad;
import itch.tsp.model.ActividadEmpleado;
import itch.tsp.repository.ActividadEmpleadoRepository;
import itch.tsp.repository.ActividadRepository;
import itch.tsp.service.IActividadService;

@Service
public class ActividadServiceImpJPA implements IActividadService {

    @Autowired
    private ActividadRepository actividadRepo;

    @Autowired
    private ActividadEmpleadoRepository actividadEmpleadoRepo; // <-- Inyectamos este repositorio

    @Override
    public List<Actividad> buscarTodas() {
        // CORRECCIÓN: Trae solo las actividades que estén activas
        return actividadRepo.findByActivo(true);
    }

    @Override
    public void guardar(Actividad actividad) {
        actividadRepo.save(actividad);
    }

    @Override
    public Actividad buscarPorId(Integer idActividad) {
        return actividadRepo.findById(idActividad).orElse(null);
    }

    @Override
    public void eliminar(Integer idActividad) {
        // 1. Buscamos la actividad por su ID
        Actividad actividad = actividadRepo.findById(idActividad).orElse(null);
        
        if (actividad != null) {
            // 2. Realizamos el borrado lógico de la actividad
            actividad.setActivo(false);
            actividadRepo.save(actividad);
            
            // 3. Desactivamos también los vínculos de empleados asignados a esta actividad
            // Esto evita inconsistencias en la tabla relacional intermedia
            List<ActividadEmpleado> asignaciones = actividadEmpleadoRepo.findAll();
            for (ActividadEmpleado asignacion : asignaciones) {
                if (asignacion.getActividad() != null && asignacion.getActividad().getIdActividad().equals(idActividad)) {
                    asignacion.setActivo(false);
                    actividadEmpleadoRepo.save(asignacion);
                }
            }
        }
    }
}