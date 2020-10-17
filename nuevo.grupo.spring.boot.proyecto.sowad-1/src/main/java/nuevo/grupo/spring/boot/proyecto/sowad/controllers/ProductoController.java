package nuevo.grupo.spring.boot.proyecto.sowad.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import nuevo.grupo.spring.boot.proyecto.sowad.models.entity.Producto;
import nuevo.grupo.spring.boot.proyecto.sowad.services.IProductoService;

@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:8080"})
@RestController
@RequestMapping("/api/v1/QW")
public class ProductoController {
	
	private final IProductoService ProductoService;

	@GetMapping(value="/productos" )
	public List<Producto> listarProductos(@RequestParam Map<String,Object> params) {
		int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString())-1) : 0;
		int size = Integer.valueOf(params.get("size").toString());
		
		PageRequest pageRequest = PageRequest.of(page,size);
		
		Page<Producto> pageProducto = ProductoService.getAll(pageRequest);
		 
		return pageProducto.getContent();
	}
	@GetMapping(value="/productos/count" )
	public int cuentaProductos() {
		return ProductoService.countProductos();
	}
	
	@GetMapping("/productos/{id}")
	public Producto show(@PathVariable Long id) {  
		return ProductoService.findByIdProducto(id);
	}
	
	@PostMapping("/productos")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto create(@RequestBody Producto producto) {
		return ProductoService.saveProducto(producto);
	}
	
	@DeleteMapping("/productos/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		ProductoService.deleteProducto(id);
	}
	
	@PutMapping("/productos/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto update(@RequestBody Producto producto, @PathVariable Long id) {
		Producto productoActual = ProductoService.findByIdProducto(id);
		productoActual.setMarca(producto.getMarca());
		productoActual.setNombre(producto.getNombre());
		productoActual.setPorcionPorPersona(producto.getPorcionPorPersona());
		productoActual.setPrecio(producto.getPrecio());
		productoActual.setStock(producto.getStock());
		productoActual.setTipo(producto.getTipo());
		return ProductoService.saveProducto(productoActual);
	}	

	public static final String GREATER_THAN="greater";
	public static final String LESS_THAN="less";
	public static final String EQUAL="equal";
	@GetMapping(value="/productos/busqueda")
	public List<Producto> listarProductosPorCriterio(@RequestParam(required=false, name="nombre") String nombre, 
													@RequestParam(required=false, name="tipo") String tipo,	
													@RequestParam(required=false, name="porcionPorPersona")Float porcionPorPersona,
													@RequestParam(required=false, name="precio")Float precio,
													@RequestParam(required=false, name="stock")Integer stock,
													@RequestParam(required=false, name="marca") String marca,
													@RequestParam Map<String,Object> params) {

		int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString())-1) : 0;
		int size = Integer.valueOf(params.get("size").toString());

		PageRequest pageRequest = PageRequest.of(page,size);

		HashMap<String, Object> data=new HashMap<>(); 
		//nombre
		if (nombre!=null) {
			data.put("nombre",nombre); 
		}  
		//tipo
		if (tipo!=null) {
			data.put("tipo",tipo); 
		}  
		//porcionPorPersona
		if (porcionPorPersona!=null) {
			data.put("porcionPorPersona",porcionPorPersona); 
		}  
		//precio
		if (precio!=null) {
			data.put("precio",precio); 
		}  
		//stock
		if (stock!=null) {
			data.put("stock",stock); 
		}  
		//nombre
		if (marca!=null) {
			data.put("marca",marca); 
		}  

		Page<Producto> pageProducto = ProductoService.getData(data, pageRequest);

		return pageProducto.getContent();
	} 

}
