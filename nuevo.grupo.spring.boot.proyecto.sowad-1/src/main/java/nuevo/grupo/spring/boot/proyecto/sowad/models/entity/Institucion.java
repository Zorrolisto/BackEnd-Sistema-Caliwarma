package nuevo.grupo.spring.boot.proyecto.sowad.models.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull; 
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name="institucion")
public class Institucion implements Serializable{
	    
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@NotEmpty
	@Size(min = 5, max = 10)
	@Column(unique = true)
	private String numero;
	@NotEmpty
	@NotNull
	private String nombre;
	private int nroDeAlumnos;
	@NotEmpty
	@NotNull
	private String nivel;

	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	private Lugar lugar;


	public void verificarSoloLetras(String string){
		for(char v : string.toCharArray()){
			if(!Character.isLetter(v)){
				throw new RuntimeException("no es solo letras");
			}
		}
	}

	public void verificarSoloNumeros(String string){
		for(char v : string.toCharArray()){
			if(!Character.isDigit(v)){
				throw new RuntimeException("no es solo numeros");
			}
		}
	}
}
