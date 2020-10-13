package nuevo.grupo.spring.boot.proyecto.sowad.services;

import java.util.HashMap;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import nuevo.grupo.spring.boot.proyecto.sowad.models.entity.Institucion;

public interface IInstitucionService {
	public Institucion findByIdInstitucion(Long idInstitucion);
	public Institucion saveInstitucion(Institucion institucion); 
	public void deleteInstitucion(Long idInstitucion);
	public Page<Institucion> getAll(Pageable pageable);
	public Page<Institucion> getData(HashMap<String, Object> conditions, Pageable pageable);
	public int countInstituciones();
}
