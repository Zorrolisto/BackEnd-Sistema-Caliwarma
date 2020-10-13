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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nuevo.grupo.spring.boot.proyecto.sowad.models.dao.ILugarDao;
import nuevo.grupo.spring.boot.proyecto.sowad.models.entity.Lugar;

@Service
public class LugarServiceImpl implements ILugarService {
    
	@Autowired 
	private ILugarDao LugarDao; 

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly=true)
	@Override
	public Lugar findByIdLugar(Long idLugar) {
		return LugarDao.findById(idLugar).orElse(null);
	}

	@Transactional
	@Override
	public Lugar saveLugar(Lugar lugar) {
		return LugarDao.save(lugar);
	}
	
	@Transactional
	@Override
	public void deleteLugar(Long idLugar) {
		LugarDao.deleteById(idLugar);
	}
	
	@Transactional(readOnly=true)
	@Override
	public Page<Lugar> getAll(Pageable pageable) {
		return LugarDao.findAll(pageable);
	}


	//BUSQEUDA AVANZAADADADADADADAD
	public static final String GREATER_THAN="greater";
	public static final String LESS_THAN="less";
	public static final String EQUAL="equal";
	@Transactional(readOnly=true)
	@Override
	public Page<Lugar> getData(HashMap<String, Object> conditions, Pageable pageable) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Lugar> query= cb.createQuery(Lugar.class);
		Root<Lugar> root = query.from(Lugar.class);
			
		List<Predicate> predicates = new ArrayList<Predicate>();
		conditions.forEach((field,value) ->
		{
			switch (field)
			{
				case "departamento":  
					predicates.add(cb.like(root.get("departamento"), "%"+(String)value+"%"));
				break;
				case "provincia":  
					predicates.add(cb.like(root.get("provincia"), "%"+(String)value+"%"));
				break;
				case "cliente":  
					predicates.add(cb.like(root.get("distrito"), "%"+(String)value+"%"));
				break;
				case "direccion":  
					predicates.add(cb.like(root.get("direccion"), "%"+(String)value+"%"));
				break;
			}
		}); 
		query.where(cb.and(predicates.toArray( new Predicate[predicates.size()])));
			
		
		List<Lugar> result = entityManager.createQuery(query).setFirstResult((int) pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList();
		
		CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
	    Root<Lugar> pedidosRootCount = countQuery.from(Lugar.class);
	    countQuery.select(cb.count(pedidosRootCount)).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
	    
	    
	    Long count = entityManager.createQuery(countQuery).getSingleResult();
		Page<Lugar> result1 = new PageImpl<>(result, pageable, count);
	    return  result1;
	}

	@Transactional(readOnly=true)
	@Override
	public int countLugares() {
		return (int)LugarDao.count();
	}

}