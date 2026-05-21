package itch.tsp.model;

import jakarta.persistence.*;

@Entity
public class Departamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String descripcion;
	public String fotoDep = "/departamentos/no-imagen-Dep.jpg";
	private Integer estado = 1;
	private Boolean activo = true; 
	
	@Override
	public String toString() {
		return "Departamento [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", activo=" + activo + ", fotoDep=" + fotoDep + "]";
	}

	// Getters y Setters
	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }
	public String getNombre() { return nombre; }
	public void setNombre(String nombre) { this.nombre = nombre; }
	public String getDescripcion() { return descripcion; }
	public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
	public String getFotoDep() { return fotoDep; }
	public void setFotoDep(String fotoDep) { this.fotoDep = fotoDep; }
	public Boolean getActivo() { return activo; }
	public void setActivo(Boolean activo) { this.activo = activo; }
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
}