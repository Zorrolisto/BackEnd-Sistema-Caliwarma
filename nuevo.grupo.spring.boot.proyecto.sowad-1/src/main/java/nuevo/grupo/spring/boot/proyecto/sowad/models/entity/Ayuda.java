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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

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

	@NotNull
	@NumberFormat(style = NumberFormat.Style.NUMBER)
	@Min(1)
	@Max(100000)
	private int porcionesTotales;

	@NotNull
	@NumberFormat(style = NumberFormat.Style.NUMBER)
	@Min(1)
	private Float precioTotal;

	@NotNull
	@ManyToOne
	private Institucion institucion;

	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="ayuda_id")
	private Set<LineaDeAyuda> lineasDeAyudas;

	public void verificarPorcionesTotales(){
		if(this.porcionesTotales<1)
			throw new RuntimeException("Porciones menores a 1");
		if(this.porcionesTotales>100000)
			throw new RuntimeException("Porciones mayores a 100000");
	}

	public void verificarPrecioTotal(){
		if(this.precioTotal<1)
			throw new RuntimeException("Precio total a 1");
	}
}
