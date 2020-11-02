package nuevo.grupo.spring.boot.proyecto.sowad.models.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import lombok.Data;

@Data
@Entity
@Table(name="institucion")
public class Institucion implements Serializable{
	    
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String numero;
	private String nombre;
	private int nroDeAlumnos;
	private String nivel;

	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	private Lugar lugar;
}
