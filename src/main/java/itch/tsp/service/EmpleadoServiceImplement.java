package itch.tsp.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import itch.tsp.model.Empleado;



@Service
public class EmpleadoServiceImplement implements IEmpleadoService {

	//Formato para las fechas
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	//Lista para almacenar los empleados
	List<itch.tsp.model.Empleado> listaEmpleados= null;
	
	//Constructor de la clase
	public EmpleadoServiceImplement() {
		listaEmpleados = new  LinkedList<>();
		
		try {
			//Crear Empleados
			Empleado emp1 = new Empleado();
			emp1.setId(1);
			emp1.setNombre("Luis");
			emp1.setApellidos("Fernández");
			emp1.setSalario(2500.0);
			emp1.setActivo(true);
			
			emp1.setFoto("/empleados/foto3.jpg");
			emp1.setFechaIngreso(sdf.parse("15/03/2015"));
			
			Empleado emp2 = new Empleado();
			emp2.setId(2);
			emp2.setNombre("Xochitl");
			emp2.setApellidos("Aranda Huerta");
			emp2.setSalario(3300.50);
			emp2.setActivo(true);
			emp2.setFoto("/empleados/foto2.jpg");
			emp2.setFechaIngreso(sdf.parse("18/12/2019"));
			
			Empleado emp3 = new Empleado();
			emp3.setId(3);
			emp3.setNombre("Jorge");
			emp3.setApellidos("López");
			emp3.setSalario(2800.75);
			emp3.setActivo(true);
			emp3.getFoto();
			emp3.setFechaIngreso(sdf.parse("22/11/2017"));
			
			Empleado emp4 = new Empleado();
			emp4.setId(4);
			emp4.setNombre("Ignacio");
			emp4.setApellidos("Romero Martínez");
			emp4.setSalario(3100.25);
			emp4.setActivo(true);
			emp4.setFoto("/empleados/perritotruste.jpg");
			emp4.setFechaIngreso(sdf.parse("10/01/2020"));
			
			//Añadir los empleados a la lista
			listaEmpleados.add(emp1);
			listaEmpleados.add(emp2);
			listaEmpleados.add(emp3);
			listaEmpleados.add(emp4);
			
		}	//Fin try
			catch(ParseException ex) {
			ex.printStackTrace();
		}
	}
	
	
	//Implementación del método de la interfaz
	@Override
	public List<Empleado> buscarTodosEmp() {
		return listaEmpleados;
	}


	@Override
	public void guardarEmp(Empleado empleado) {
		listaEmpleados.add(empleado);
		
		
	}
	
	@Override
	public Empleado buscarEmpPorId(Integer id) {
		for(Empleado emp: listaEmpleados) {
			if(emp.getId() == id) {
				return emp;
			}
			
		}
		return null;
	}

}
