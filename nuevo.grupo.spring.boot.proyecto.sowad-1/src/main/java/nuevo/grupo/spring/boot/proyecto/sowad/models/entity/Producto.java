package nuevo.grupo.spring.boot.proyecto.sowad.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;

import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

@Data
@Entity
@Table(name="producto")
public class Producto implements Serializable{
    
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotEmpty
	@NotNull
	private String nombre;
	@NotEmpty
	@NotNull
	private String tipo;
	@NotEmpty
	@NumberFormat(style = NumberFormat.Style.NUMBER)
	@Min(0)
	@Max(100)
	private Float porcionPorPersona;
	@NotEmpty
	@NumberFormat(style = NumberFormat.Style.NUMBER)
	@Min((long)0.1)
	@Max(100000)
	private float precio;
	@NotEmpty
	@NumberFormat(style = NumberFormat.Style.NUMBER)
	@Min(1)
	@Max(100000)
	private int stock;
	@NotEmpty
	@NotNull
	private String marca;


	public void verificarSoloLetras(String string){
		for(char v : string.toCharArray()){
			if(!Character.isLetter(v)){
				throw new RuntimeException("no es solo letras");
			}
		}
	}
}
