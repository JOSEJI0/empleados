package itch.tsp.service.implementJPA;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import itch.tsp.model.Empleado;
import itch.tsp.repository.EmpleadoRepository;
import itch.tsp.service.IEmpleadoService;

@Primary
@Service
public class EmpleadoServiceImpJPA implements IEmpleadoService {
	
	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@Override
	public List<Empleado> buscarTodosEmp() {
		
		return empleadoRepository.findByActivo(1);
	}

	@Override
	public void guardarEmp(Empleado emp) {
		empleadoRepository.save(emp);

	}

	@Override
	public Empleado buscarEmpPorId(Integer idEmpleado) {
		Optional<Empleado> optionalEmpleado = empleadoRepository.findById(idEmpleado);
		
		if (optionalEmpleado.isPresent()) {
			return optionalEmpleado.get();
		}
		return null;
	}
	
	@Override
	public void eliminarEmp(Integer idEmpleado) {
		Optional<Empleado> optionalEmpleado = empleadoRepository.findById(idEmpleado);
		
		if (optionalEmpleado.isPresent()) {
			Empleado emp = optionalEmpleado.get();
			emp.setActivo(0);
			empleadoRepository.save(emp);
		}
	}

}
