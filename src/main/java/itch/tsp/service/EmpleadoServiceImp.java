package itch.tsp.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import itch.tsp.model.Empleado;
@Service
public class EmpleadoServiceImp implements IEmpleadoService {
	
	SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
	
	List<Empleado> listaEmp=null;
	
	public EmpleadoServiceImp() {
		listaEmp= new LinkedList<Empleado>();
		
		try {
			Empleado emp1= new Empleado();
			emp1.setId(2);
			emp1.setNombreE("Juan");
			emp1.setApellido("Perez");
			emp1.setSalario(300.3);
			emp1.setFechaIngreso(sdf.parse("06-02-2026"));
			emp1.setActivo(1);
			emp1.setFoto("empleadoH.png");
			
			Empleado emp2= new Empleado();
			emp2.setId(3);
			emp2.setNombreE("Maria");
			emp2.setApellido("Gomez");
			emp2.setSalario(400.5);
			emp2.setFechaIngreso(sdf.parse("15-03-2025"));
			emp2.setActivo(0);
			emp2.setFoto("empleadoM.jpg");
			
			Empleado emp3= new Empleado();
			emp3.setId(4);
			emp3.setNombreE("Carlos");
			emp3.setApellido("Lopez");
			emp3.setSalario(500.7);
			emp3.setFechaIngreso(sdf.parse("20-01-2024"));

			
			listaEmp.add(emp1);
			listaEmp.add(emp2);
			listaEmp.add(emp3);
		
		} catch(ParseException e) {
			e.printStackTrace();
			
		}
	}

	@Override
	public List<Empleado> buscarTodosEmp() {
		return listaEmp;
	}
	
	@Override
	public void guardarEmp(Empleado emp) {
		listaEmp.add(emp);
	}
	
	@Override
	public Empleado buscarEmpPorId(Integer idEmpleado) {
		for(Empleado emp: listaEmp)
			if(emp.getId()==idEmpleado) {
				return emp;
			}
		return null;
	}

	@Override
	public void eliminarEmp(Integer idEmpleado) {
		// TODO Auto-generated method stub
		
	}

}