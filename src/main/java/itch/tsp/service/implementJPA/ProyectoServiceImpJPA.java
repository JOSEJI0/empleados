package itch.tsp.service.implementJPA;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import itch.tsp.model.Proyecto;
import itch.tsp.repository.ProyectoRepository;
import itch.tsp.service.IProyectoService;

@Service
public class ProyectoServiceImpJPA implements IProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepo;

    @Override
    public void guardar(Proyecto proyecto) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date hoy = cal.getTime();
        
        // REGLA: No podrá asignar fechas pasadas al día de su creación 
        if (proyecto.getId() == null && proyecto.getFechaInicio() != null) {
            if (proyecto.getFechaInicio().before(hoy)) {
                throw new IllegalArgumentException("La fecha de inicio no puede ser anterior al día de hoy.");
            }
        }
        
        // REGLA: La fecha final no puede ser igual o anterior a la fecha de inicio 
        if (proyecto.getFechaInicio() != null && proyecto.getFechaFin() != null) {
            // .after() es estrictamente posterior. Si son iguales, lanza la excepción.
            if (!proyecto.getFechaFin().after(proyecto.getFechaInicio())) {
                throw new IllegalArgumentException("La fecha final debe ser estrictamente posterior a la fecha de inicio.");
            }
        }
        
        // Asegurar que el estatus activo sea 1 si es un registro nuevo
        if (proyecto.getId() == null) {
            proyecto.setActivo(1);
        }
        
        proyectoRepo.save(proyecto);
    }

    @Override
    public void eliminarLogico(Integer id) {
        Optional<Proyecto> optional = proyectoRepo.findById(id);
        if (optional.isPresent()) {
            Proyecto p = optional.get();
            p.setActivo(0); // Estatus 0 para eliminación lógica 
            proyectoRepo.save(p);
        }
    }

    @Override
    public List<Proyecto> buscarParaInicio() {
        // Activos (1) y sin fecha de fin
        return proyectoRepo.findByActivoAndFechaFinIsNull(1);
    }

    @Override
    public List<Proyecto> buscarTodosActivos() {
        return proyectoRepo.findByActivo(1);
    }

    @Override
    public Proyecto buscarPorId(Integer id) {
        return proyectoRepo.findById(id).orElse(null);
    }

    @Override
    public List<Proyecto> buscarPorFiltrosInicio(String nombre, Date inicio, Date fin) {
        String nom = (nombre != null && !nombre.isEmpty()) ? nombre : null;
        return proyectoRepo.buscarProyectosInicioConFiltros(nom, inicio, fin);
    }
}