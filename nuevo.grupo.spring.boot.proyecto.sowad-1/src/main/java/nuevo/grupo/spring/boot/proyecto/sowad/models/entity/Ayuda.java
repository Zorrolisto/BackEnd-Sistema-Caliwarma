package nuevo.grupo.spring.boot.proyecto.sowad.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name="ayuda")
public class Ayuda  implements Serializable{
    
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Temporal(TemporalType.DATE)
	private Date fechaDeLlegada;

	@NotNull
	@Temporal(TemporalType.DATE)
	private Date fechaDeEnvio;

	@NotNull
	@Temporal(TemporalType.DATE)
	private Date fechaDeRegistro;

	private int porcionesTotales;
	@Min(1)
	private Float precioTotal;

	@NotNull
	@ManyToOne
	private Institucion institucion;

	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="ayuda_id")
	private Set<LineaDeAyuda> lineasDeAyudas;
}
