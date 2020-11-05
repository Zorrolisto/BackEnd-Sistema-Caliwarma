package nuevo.grupo.spring.boot.proyecto.sowad.services;

import java.util.HashMap;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import nuevo.grupo.spring.boot.proyecto.sowad.models.entity.Ayuda;

public interface IAyudaService {
	public Ayuda findByIdAyuda(Long idAyuda);
	public Ayuda saveAyuda(Ayuda ayuda); 
	public void deleteAyuda(Long idAyuda);
	public Page<Ayuda> getAll(Pageable pageable);
	public Page<Ayuda> getData(HashMap<String, Object> conditions, Pageable pageable);
	public int countAyudas();
	Ayuda findFirstByOrderByIdDesc();
}
