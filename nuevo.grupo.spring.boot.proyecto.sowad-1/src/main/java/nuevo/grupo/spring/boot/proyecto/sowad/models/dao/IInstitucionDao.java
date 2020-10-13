package nuevo.grupo.spring.boot.proyecto.sowad.models.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import nuevo.grupo.spring.boot.proyecto.sowad.models.entity.Institucion;

public interface IInstitucionDao extends CrudRepository<Institucion, Long>{
	public Page<Institucion> findAll(Pageable pageable);
}
