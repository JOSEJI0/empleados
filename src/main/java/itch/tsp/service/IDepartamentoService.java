package itch.tsp.service;
import java.util.List;
import itch.tsp.model.Departamento;

public interface IDepartamentoService {
    List<Departamento> buscarTodos();
    void guardar(Departamento departamento);
    
	Departamento buscarDepPorId(Integer idDepartamento);
	
	void eliminarDepto(Integer idDepartamento);
}