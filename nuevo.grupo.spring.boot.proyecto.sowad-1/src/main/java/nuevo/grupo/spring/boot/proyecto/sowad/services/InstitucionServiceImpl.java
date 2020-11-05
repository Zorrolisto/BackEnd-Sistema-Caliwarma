package nuevo.grupo.spring.boot.proyecto.sowad.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;

import nuevo.grupo.spring.boot.proyecto.sowad.models.entity.Lugar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import nuevo.grupo.spring.boot.proyecto.sowad.models.dao.IInstitucionDao;
import nuevo.grupo.spring.boot.proyecto.sowad.models.entity.Institucion;

@RequiredArgsConstructor
@Service
public class InstitucionServiceImpl implements IInstitucionService{
    
	private final IInstitucionDao InstitucionDao; 

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly=true)
	@Override
	public Institucion findByIdInstitucion(Long idInstitucion) {
		return InstitucionDao.findById(idInstitucion).orElse(null);
	}

	@Transactional
	@Override
	public Institucion saveInstitucion(Institucion institucion) {
		Lugar lugar = institucion.getLugar();
		lugar.verificarSoloLetras(lugar.getDistrito());
		lugar.verificarSoloLetras(lugar.getDepartamento());
		lugar.verificarSoloLetras(lugar.getProvincia());
		institucion.verificarSoloNumeros(institucion.getNumero());
		institucion.verificarSoloLetras(institucion.getNombre());
		institucion.verificarSoloLetras(institucion.getNivel());
		return InstitucionDao.save(institucion);
	}

	@Transactional
	@Override
	public void deleteInstitucion(Long idInstitucion) {
		InstitucionDao.deleteById(idInstitucion);
	}

	@Transactional(readOnly=true)
	@Override
	public Page<Institucion> getAll(Pageable pageable) {
		return InstitucionDao.findAll(pageable);
	}

	@Transactional(readOnly=true)
	@Override
	public Page<Institucion> getData(HashMap<String, Object> conditions, Pageable pageable) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Institucion> query= cb.createQuery(Institucion.class);
		Root<Institucion> root = query.from(Institucion.class);
			
		List<Predicate> predicates = new ArrayList<Predicate>();
		conditions.forEach((field,value) ->
		{
			switch (field)
			{
				case "departamento":
					CriteriaQuery<Lugar> criteria1= cb.createQuery(Lugar.class);
					Root<Lugar> rootCriteria1 = criteria1.from(Lugar.class);
					Predicate predicate1 = cb.like(rootCriteria1.get("departamento"), "%"+(String)value+"%");
					criteria1.select(rootCriteria1).where(predicate1);
					List<Long> myListIDsLugares1 = new ArrayList<Long> ();
					for (Lugar u : entityManager.createQuery(criteria1).getResultList()) {
						myListIDsLugares1.add(u.getId());
					}
					Expression<Lugar> expLugares1= root.get("lugar");
					predicates.add(expLugares1.in(myListIDsLugares1));
					break;
				case "provincia":
					CriteriaQuery<Lugar> criteria2= cb.createQuery(Lugar.class);
					Root<Lugar> rootCriteria2 = criteria2.from(Lugar.class);
					Predicate predicate2 = cb.like(rootCriteria2.get("provincia"), "%"+(String)value+"%");
					criteria2.select(rootCriteria2).where(predicate2);
					List<Long> myListIDsLugares2 = new ArrayList<Long> ();
					for (Lugar u : entityManager.createQuery(criteria2).getResultList()) {
						myListIDsLugares2.add(u.getId());
					}
					Expression<Lugar> expLugares2= root.get("lugar");
					predicates.add(expLugares2.in(myListIDsLugares2));
					break;
				case "distrito":
					CriteriaQuery<Lugar> criteria3= cb.createQuery(Lugar.class);
					Root<Lugar> rootCriteria3 = criteria3.from(Lugar.class);
					Predicate predicate3 = cb.like(rootCriteria3.get("distrito"), "%"+(String)value+"%");
					criteria3.select(rootCriteria3).where(predicate3);
					List<Long> myListIDsLugares3 = new ArrayList<Long> ();
					for (Lugar u : entityManager.createQuery(criteria3).getResultList()) {
						myListIDsLugares3.add(u.getId());
					}
					Expression<Lugar> expLugares3= root.get("lugar");
					predicates.add(expLugares3.in(myListIDsLugares3));
					break;
				case "direccion":
					CriteriaQuery<Lugar> criteria4= cb.createQuery(Lugar.class);
					Root<Lugar> rootCriteria4 = criteria4.from(Lugar.class);
					Predicate predicate4 = cb.like(rootCriteria4.get("direccion"), "%"+(String)value+"%");
					criteria4.select(rootCriteria4).where(predicate4);
					List<Long> myListIDsLugares4 = new ArrayList<Long> ();
					for (Lugar u : entityManager.createQuery(criteria4).getResultList()) {
						myListIDsLugares4.add(u.getId());
					}
					Expression<Lugar> expLugares4= root.get("lugar");
					predicates.add(expLugares4.in(myListIDsLugares4));
					break;
				case "nombre":  
					predicates.add(cb.like(root.get("nombre"), "%"+(String)value+"%"));
				break;
				case "nroDeAlumnos":  
					predicates.add(cb.equal(root.get("nroDeAlumnos"), (Integer)value));
				break;
				case "nivel":  
					predicates.add(cb.like(root.get("nivel"), "%"+(String)value+"%"));
				break;
				case "numero":  
					predicates.add(cb.like(root.get("numero"), "%"+(String)value+"%"));
				break;
			}
		}); 
		query.where(cb.and(predicates.toArray( new Predicate[predicates.size()])));
			
		
		List<Institucion> result = entityManager.createQuery(query).setFirstResult((int) pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList();
		
		CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
	    Root<Institucion> pedidosRootCount = countQuery.from(Institucion.class);
	    countQuery.select(cb.count(pedidosRootCount)).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
	    
	    
	    Long count = entityManager.createQuery(countQuery).getSingleResult();
		Page<Institucion> result1 = new PageImpl<>(result, pageable, count);
	    return  result1;
	}

	@Transactional(readOnly=true)
	@Override
	public int countInstituciones() {
		return (int)InstitucionDao.count();
	}

	@Override
	public Institucion findByNumero(String numero) {
		return InstitucionDao.findByNumero(numero);
	}

}
