package nuevo.grupo.spring.boot.proyecto.sowad.models.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import nuevo.grupo.spring.boot.proyecto.sowad.models.entity.Lugar;

public interface ILugarDao extends CrudRepository<Lugar, Long>{
	public Page<Lugar> findAll(Pageable pageable);
}