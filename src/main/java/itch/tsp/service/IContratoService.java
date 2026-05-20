package itch.tsp.service;

import java.util.List;

import itch.tsp.model.Contrato;

public interface IContratoService {
	List<Contrato> buscarTodosContrato();
	void guardarContrato(Contrato contrato);
	Contrato buscarContratoPorId(Integer idContrato);
	void eliminarContrato(Integer idContrato);
	List<Contrato> buscarPorFiltros(String nombre, String numero, String tipo);
}
