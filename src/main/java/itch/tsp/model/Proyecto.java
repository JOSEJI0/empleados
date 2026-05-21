package itch.tsp.model;

import java.util.Date;
import jakarta.persistence.*;

@Entity
public class Proyecto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private Date fechaInicio;
	private Date fechaFin;
	private String descripcion;
	private Integer estado = 1;
	private Boolean activo = true;

	public enum StatusProyecto {
		INICIO, EN_PROCESO, FINALIZADO, CANCELADO
	}

	@Enumerated(EnumType.STRING)
	private StatusProyecto statusProyecto;

	public Proyecto() {
	}

	// Getters y Setters
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

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public StatusProyecto getStatusProyecto() {
		return statusProyecto;
	}

	public void setStatusProyecto(StatusProyecto statusProyecto) {
		this.statusProyecto = statusProyecto;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return "Proyecto [id=" + id + ", nombre=" + nombre + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin
				+ ", descripcion=" + descripcion + ", statusProyecto=" + statusProyecto + "]";
	}
}