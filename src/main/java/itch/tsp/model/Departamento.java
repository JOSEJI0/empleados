package itch.tsp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "departamento")
public class Departamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "nombre", nullable = false, length = 50)
	private String nombre;
	@Column(name = "activo", nullable = true)
	private Integer activo;
	@Column(name = "fotoUbicacion", nullable = true, length = 250)
	private String fotoUbicacion = "no-imagen.jpg";
	@Column(name = "descripcion", nullable = true, length = 250)
	private String descripcion;

	public String getDescripcion() {
	    return descripcion;
	}

	public void setDescripcion(String descripcion) {
	    this.descripcion = descripcion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getActivo() {
		return activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

	public String getFotoUbicacion() {
		return fotoUbicacion;
	}

	public void setFotoUbicacion(String fotoUbicacion) {
		this.fotoUbicacion = fotoUbicacion;
	}

	@Override
	public String toString() {
		return "Departamento [id=" + id + ", nombre=" + nombre + ", activo=" + activo
				+ ", fotoUbicacion=" + fotoUbicacion + "]";
	}
}