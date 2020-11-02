package nuevo.grupo.spring.boot.proyecto.sowad.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import nuevo.grupo.spring.boot.proyecto.sowad.models.dao.IProductoDao;
import nuevo.grupo.spring.boot.proyecto.sowad.models.entity.Producto;

@RequiredArgsConstructor
@Service
public class ProductoServiceImpl implements IProductoService{
    
	private final IProductoDao ProductoDao; 

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly=true)
	@Override
	public Producto findByIdProducto(Long idProducto) {
		return ProductoDao.findById(idProducto).orElse(null);
	}

	@Transactional
	@Override
	public Producto saveProducto(Producto producto) {
		return ProductoDao.save(producto);
	}

	@Transactional
	@Override
	public void deleteProducto(Long idProducto) {
		ProductoDao.deleteById(idProducto);
	}
	
	@Transactional(readOnly=true)
	@Override
	public Page<Producto> getAll(Pageable pageable) {
		return ProductoDao.findAll(pageable);
	}

	//BUSQEUDA
	public static final String GREATER_THAN="greater";
	public static final String LESS_THAN="less";
	public static final String EQUAL="equal";
	@Transactional(readOnly=true)
	@Override
	public Page<Producto> getData(HashMap<String, Object> conditions, Pageable pageable) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Producto> query= cb.createQuery(Producto.class);
		Root<Producto> root = query.from(Producto.class);
			
		List<Predicate> predicates = new ArrayList<Predicate>();
		conditions.forEach((field,value) ->
		{
			switch (field)
			{
				case "nombre":  
					predicates.add(cb.like(root.get("nombre"), "%"+(String)value+"%"));
					break;
				case "tipo":  
					predicates.add(cb.like(root.get("tipo"), "%"+(String)value+"%"));
					break;
				case "porcionPorPersona":  
					predicates.add(cb.equal(root.get("porcionPorPersona"), (Float)value));
					break;
				case "precio":
					String precioCondicion=(String) conditions.get("precioCondicion");
					switch (precioCondicion)
					{
						case GREATER_THAN:
							predicates.add(cb.greaterThan(root.<Float>get(field),(Float)value));
							break;
						case LESS_THAN:
							predicates.add(cb.lessThan(root.<Float>get(field),(Float)value));
							break;
						case EQUAL:
							predicates.add(cb.equal(root.<Float>get(field),(Float)value));
							break;
					}
					break;
				case "stock":
					String stockCondition=(String) conditions.get("stockCondicion");
					switch (stockCondition)
					{
						case GREATER_THAN:
							predicates.add(cb.greaterThan(root.<Integer>get(field),(Integer)value));
							break;
						case LESS_THAN:
							predicates.add(cb.lessThan(root.<Integer>get(field),(Integer)value));
							break;
						case EQUAL:
							predicates.add(cb.equal(root.<Integer>get(field),(Integer)value));
							break;
					}
					break;
				case "marca":  
					predicates.add(cb.like(root.get("marca"), "%"+(String)value+"%"));
					break;
			}
		}); 
		query.where(cb.and(predicates.toArray( new Predicate[predicates.size()])));
			
		
		List<Producto> result = entityManager.createQuery(query).setFirstResult((int) pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList();
		
		CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
	    Root<Producto> pedidosRootCount = countQuery.from(Producto.class);
	    countQuery.select(cb.count(pedidosRootCount)).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
	    
	    
	    Long count = entityManager.createQuery(countQuery).getSingleResult();
		Page<Producto> result1 = new PageImpl<>(result, pageable, count);
	    return  result1;
	}

	@Transactional(readOnly=true)
	@Override
	public int countProductos() {
		return (int)ProductoDao.count();
	}

}
