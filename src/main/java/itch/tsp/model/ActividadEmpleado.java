package itch.tsp.model;

import java.util.Date;
import jakarta.persistence.*;

@Entity
public class ActividadEmpleado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // <-- DESCOMENTADO
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_empleado")
	private Empleado empleado;
	
	@ManyToOne
	@JoinColumn(name = "id_actividad")
	private Actividad actividad;
	
	private Date fechaAsignacion;
	
	private Boolean activo = true; // <-- Borrado Lógico
	
	public ActividadEmpleado() {}

    // Getters y Setters
	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }
	public Empleado getEmpleado() { return empleado; }
	public void setEmpleado(Empleado empleado) { this.empleado = empleado; }
	public Actividad getActividad() { return actividad; }
	public void setActividad(Actividad actividad) { this.actividad = actividad; }
	public Date getFechaAsignacion() { return fechaAsignacion; }
	public void setFechaAsignacion(Date fechaAsignacion) { this.fechaAsignacion = fechaAsignacion; }
	public Boolean getActivo() { return activo; }
	public void setActivo(Boolean activo) { this.activo = activo; }

	@Override
	public String toString() {
		return "ActividadEmpleado [id=" + id + ", empleado=" + empleado + ", actividad=" + actividad + ", fechaAsignacion=" + fechaAsignacion + "]";
	}
}