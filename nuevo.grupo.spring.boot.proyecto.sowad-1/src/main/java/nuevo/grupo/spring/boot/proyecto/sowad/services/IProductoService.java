package nuevo.grupo.spring.boot.proyecto.sowad.services;

import java.util.HashMap;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import nuevo.grupo.spring.boot.proyecto.sowad.models.entity.Producto;

public interface IProductoService {
	public Producto findByIdProducto(Long idProducto);
	public Producto saveProducto(Producto producto); 
	public void deleteProducto(Long idProducto);
	public Page<Producto> getAll(Pageable pageable);
	public Page<Producto> getData(HashMap<String, Object> conditions, Pageable pageable);
	public int countProductos();
	Producto findByNombre(String nombre);
}
