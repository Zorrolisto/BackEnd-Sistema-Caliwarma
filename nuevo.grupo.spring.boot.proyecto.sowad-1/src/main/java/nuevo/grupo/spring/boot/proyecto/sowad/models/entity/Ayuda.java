package nuevo.grupo.spring.boot.proyecto.sowad.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="ayuda")
public class Ayuda  implements Serializable{
    
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.DATE)
	private Date fechaDeLlegada;

	@Temporal(TemporalType.DATE)
	private Date fechaDeEnvio;

	@Temporal(TemporalType.DATE)
	private Date fechaDeRegistro;

	private int porcionesTotales;
	private Float precioTotal;
	
	@ManyToOne
	private Institucion institucion;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="ayuda_id", referencedColumnName="id" )
	private List<LineaDeAyuda> lineasDeAyudas;
}
