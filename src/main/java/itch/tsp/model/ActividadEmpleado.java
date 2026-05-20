package itch.tsp.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ActividadEmpleado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "empleado_id")
	private Empleado empleado;
	
	@ManyToOne
	@JoinColumn(name = "actividad_id")
	private Actividad actividad;
	
	@Column(name = "fechaAsignacion", nullable = true)
	private Date fechaAsignacion;
	
	public ActividadEmpleado() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public Date getFechaAsignacion() {
		return fechaAsignacion;
	}

	public void setFechaAsignacion(Date fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}

	@Override
	public String toString() {
		return "ActividadEmpleado [id=" + id + ", empleado=" + empleado + ", actividad=" + actividad
				+ ", fechaAsignacion=" + fechaAsignacion + "]";
	}
	
	
}
