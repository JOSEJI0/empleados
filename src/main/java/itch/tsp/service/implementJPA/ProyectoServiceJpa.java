package itch.tsp.service.implementJPA;

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
		// TODO Auto-generated method stub
		return proyectoRepository.findAll();
	}

	@Override
	public void guardar(Proyecto proyecto) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		proyectoRepository.deleteById(id);
		
	}

}
