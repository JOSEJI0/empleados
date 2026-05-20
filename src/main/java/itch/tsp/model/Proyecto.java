package itch.tsp.model;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "Proyecto")
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;   

    @Enumerated(EnumType.STRING)
    private StatusProyecto statusProyecto;
    
    public enum StatusProyecto {
		inicio,
		en_proceso,
		finalizado,
		cancelado
	}	
    
    @Column(name = "nombre", nullable = true, length = 100, unique = true)
    private String nombre;
    @Column(name = "fechaInicio", nullable = true)
    private Date fechaInicio;
    @Column(name = "fechaFin", nullable = true)
    private Date fechaFin;
    @Column(name = "descripcion", nullable = true)
    private String descripcion;
    @Column(name = "activo", nullable = true)
    private Integer activo;



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
    
    public Integer getActivo() {
		return activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return "Proyecto [id=" + id + ", statusProyecto=" + statusProyecto + ", nombre=" + nombre + ", fechaInicio="
				+ fechaInicio + ", fechaFin=" + fechaFin + ", descripcion=" + descripcion + ", activo=" + activo + "]";
	}
 
}