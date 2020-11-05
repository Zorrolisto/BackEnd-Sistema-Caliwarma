package nuevo.grupo.spring.boot.proyecto.sowad.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
@Entity
@Table(name="lugar")
public class Lugar  implements Serializable{
	    
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotEmpty
	@NotNull
	private String departamento;
	@NotEmpty
	@NotNull
	private String provincia;
	@NotEmpty
	@NotNull
	private String distrito;
	@NotEmpty
	@NotNull
	private String direccion;


	public void verificarSoloLetras(String string){
		for(char v : string.toCharArray()){
			if(!Character.isLetter(v)){
				throw new RuntimeException("no es solo letras");
			}
		}
	}
}