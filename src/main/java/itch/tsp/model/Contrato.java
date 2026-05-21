package itch.tsp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Contrato")
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "numeroSeguroSocial", nullable = true, length = 50)
    private String numeroSeguroSocial;
    
    @Column(name = "tipoContrato", nullable = true, length = 50)
    private String tipoContrato;
    
    @Column(name = "activo", nullable = true)
    private Integer activo;

    @OneToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;
    
    public Contrato() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
	
    public Integer getActivo() { return activo; }
    public void setActivo(Integer activo) { this.activo = activo; }

    public String getNumeroSeguroSocial() { return numeroSeguroSocial; }
    public void setNumeroSeguroSocial(String numeroSeguroSocial) { this.numeroSeguroSocial = numeroSeguroSocial; }

    public String getTipoContrato() { return tipoContrato; }
    public void setTipoContrato(String tipoContrato) { this.tipoContrato = tipoContrato; }

    public Empleado getEmpleado() { return empleado; }
    public void setEmpleado(Empleado empleado) { this.empleado = empleado; }

    @Override
    public String toString() {
        return "Contrato [id=" + id + ", numeroSeguroSocial=" + numeroSeguroSocial + ", tipoContrato=" + tipoContrato
                + ", activo=" + activo + "]";
    }
}