package itch.tsp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Habilidad")
public class Habilidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nombre", nullable = true, unique = true, length = 50)
    private String nombre;
    @Column(name = "descripcion", nullable = true, length = 250)
    private String descripcion;
    
    public Habilidad() {
    	
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Habilidad [nombre=" + nombre + "]";
	}
    
    

}