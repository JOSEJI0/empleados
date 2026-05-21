package itch.tsp.model;

import jakarta.persistence.*;

@Entity
public class Contrato {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "numero_seguro_social")
	private String noSeguroSocial;
	
	private String tipo_contrato;		
	
	@ManyToOne
	@JoinColumn(name = "id_emp")
	private Empleado empleado;
	private Integer estado = 1;
	private Boolean activo = true;

	public Contrato() {}

    // Getters y Setters
	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }
	public String getNoSeguroSocial() { return noSeguroSocial; }
	public void setNoSeguroSocial(String noSeguroSocial) { this.noSeguroSocial = noSeguroSocial; }
	public String getTipoContrato() { return tipo_contrato; }
	public void setTipoContrato(String tipoContrato) { this.tipo_contrato = tipoContrato; }
	public Empleado getEmpleado() { return empleado; }
	public void setEmpleado(Empleado empleado) { this.empleado = empleado; }
	public Boolean getActivo() { return activo; }
	public void setActivo(Boolean activo) { this.activo = activo; }
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	@Override
    public String toString() {
        return "Contrato [ID=" + id + ", NSS=" + noSeguroSocial + ", Tipo=" + tipo_contrato + ", Empleado=" + (empleado != null ? empleado.getNombre() : "N/A") + "]";
    }
}