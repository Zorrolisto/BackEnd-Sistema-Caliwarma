package nuevo.grupo.spring.boot.proyecto.sowad.models.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import nuevo.grupo.spring.boot.proyecto.sowad.models.entity.Ayuda;

public interface IAyudaDao extends CrudRepository<Ayuda, Long>{
	public Page<Ayuda> findAll(Pageable pageable);
	Ayuda findFirstByOrderByIdDesc();
}
