package itch.tsp.service;

import java.util.List;

import itch.tsp.model.Contrato;

public interface IContratoService {
    List<Contrato> buscarTodos();
    void guardar(Contrato contrato);
    Contrato buscarPorId(Integer id);
    void eliminar(Integer id);
}