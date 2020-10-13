package nuevo.grupo.spring.boot.proyecto.sowad.models.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import nuevo.grupo.spring.boot.proyecto.sowad.models.entity.Producto;

public interface IProductoDao extends CrudRepository<Producto, Long>{
	public Page<Producto> findAll(Pageable pageable);
}
