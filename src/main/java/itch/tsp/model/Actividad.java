package itch.tsp.model;

import java.util.Date;
import jakarta.persistence.*;

@Entity
public class Actividad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idActividad;
	private String nombre;
	private String descripcion;
	private Date fechaInicio;
	private Date fechaFin;
	
	private Boolean activo = true; 
	
	@ManyToOne
	@JoinColumn(name = "id_proyecto")
	private Proyecto proyecto;
	
	public Actividad() {}

    // Getters y Setters
	public Integer getIdActividad() { return idActividad; }
	public void setIdActividad(Integer idActividad) { this.idActividad = idActividad; }
	public String getNombre() { return nombre; }
	public void setNombre(String nombre) { this.nombre = nombre; }
	public String getDescripcion() { return descripcion; }
	public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
	public Date getFechaInicio() { return fechaInicio; }
	public void setFechaInicio(Date fechaInicio) { this.fechaInicio = fechaInicio; }
	public Date getFechaFin() { return fechaFin; }
	public void setFechaFin(Date fechaFin) { this.fechaFin = fechaFin; }
	public Proyecto getProyecto() { return proyecto; }
	public void setProyecto(Proyecto proyecto) { this.proyecto = proyecto; }
	public Boolean getActivo() { return activo; }
	public void setActivo(Boolean activo) { this.activo = activo; }

	@Override
	public String toString() {
		return "Actividad [idActividad=" + idActividad + ", nombre=" + nombre + ", descripcion=" + descripcion + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", proyecto=" + proyecto + "]";
	}
}