package itch.tsp.service.implementJPA;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import itch.tsp.model.Proyecto;
import itch.tsp.repository.ProyectoRepository;
import itch.tsp.service.IProyectoService;

@Primary
@Service
public class ProyectoServiceJpa implements IProyectoService {
	
	@Autowired
	private ProyectoRepository proyectoRepository;

	@Override
	public List<Proyecto> buscarTodos() {
		return proyectoRepository.findByActivoTrue();
	}

	@Override
	public void guardar(Proyecto proyecto) {
		proyectoRepository.save(proyecto);
	}

	@Override
    public Proyecto buscarPorId(Integer id) {
        Optional<Proyecto> optional = proyectoRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

	@Override
	public void eliminar(Integer id) {
	    Proyecto proyecto = proyectoRepository.findById(id).orElse(null);
	    
	    if (proyecto != null) {
	        proyecto.setActivo(false);
	        
	        proyectoRepository.save(proyecto);
	    }
	}

	@Override
	public List<Proyecto> findProyectosActivosSinFechaFin() {
	    return proyectoRepository.findProyectosActivosSinFechaFin();
	}

	@Override
	public List<Proyecto> buscarPorFiltros(String nombre, Date fechaInicio) {
	    if (nombre != null && !nombre.isEmpty() && fechaInicio != null) {
	        return proyectoRepository.findByNombreContainingAndFechaInicioAndActivoTrueAndFechaFinIsNull(nombre, fechaInicio);
	    } else if (nombre != null && !nombre.isEmpty()) {
	        return proyectoRepository.findByNombreContainingAndActivoTrueAndFechaFinIsNull(nombre);
	    } else if (fechaInicio != null) {
	        return proyectoRepository.findByFechaInicioAndActivoTrueAndFechaFinIsNull(fechaInicio);
	    }
	    return proyectoRepository.findProyectosActivosSinFechaFin();
	}
}