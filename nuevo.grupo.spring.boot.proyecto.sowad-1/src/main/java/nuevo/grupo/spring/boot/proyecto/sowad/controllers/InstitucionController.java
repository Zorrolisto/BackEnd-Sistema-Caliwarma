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
import nuevo.grupo.spring.boot.proyecto.sowad.models.entity.Institucion;
import nuevo.grupo.spring.boot.proyecto.sowad.services.IInstitucionService;

@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:8080"})
@RestController
@RequestMapping("/api/v1/QW")
public class InstitucionController {
	
	private final IInstitucionService InstitucionService;

	@GetMapping(value="/instituciones" )
	public List<Institucion> listarInstituciones(@RequestParam Map<String,Object> params) {
		int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString())-1) : 0;
		int size = Integer.valueOf(params.get("size").toString());
		
		PageRequest pageRequest = PageRequest.of(page,size);
		
		Page<Institucion> pageInstitucion = InstitucionService.getAll(pageRequest);
		 
		return pageInstitucion.getContent();
	}
	@GetMapping(value="/instituciones/count" )
	public int cuentaPedidos() {
		return InstitucionService.countInstituciones();
	}

	@GetMapping("/instituciones/{id}")
	public Institucion show(@PathVariable Long id) {  
		return InstitucionService.findByIdInstitucion(id);
	}
	
	@PostMapping("/instituciones")
	@ResponseStatus(HttpStatus.CREATED)
	public Institucion create(@RequestBody Institucion institucion) {
		return InstitucionService.saveInstitucion(institucion);
	}
	
	@DeleteMapping("/instituciones/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		InstitucionService.deleteInstitucion(id);
	}
	
	@PutMapping("/instituciones/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Institucion update(@RequestBody Institucion institucion, @PathVariable Long id) {
		Institucion institucionActual = InstitucionService.findByIdInstitucion(id);
		institucionActual.setLugar(institucion.getLugar());
		institucionActual.setNivel(institucion.getNivel());
		institucionActual.setNombre(institucion.getNombre());
		institucionActual.setNroDeAlumnos(institucion.getNroDeAlumnos());
		institucionActual.setNumero(institucion.getNumero());
		return InstitucionService.saveInstitucion(institucionActual);
	}

	@GetMapping(value="/instituciones/busqueda")
	public List<Institucion> listarInstitucionesPorCriterio(@RequestParam(required=false, name="departamento") String departamento,
															@RequestParam(required=false, name="provincia") String provincia,
															@RequestParam(required=false, name="distrito") String distrito,
															@RequestParam(required=false, name="direccion") String direccion,
															@RequestParam(required=false, name="nombre") String nombre,	
															@RequestParam(required=false, name="nroDeAlumnos")Integer nroDeAlumnos,
															@RequestParam(required=false, name="nivel") String nivel,	
															@RequestParam(required=false, name="numero") String numero,	
															@RequestParam(required=false, name="lugar") String lugar,	
															@RequestParam Map<String,Object> params) {

		int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString())-1) : 0;
		int size = Integer.valueOf(params.get("size").toString());

		PageRequest pageRequest = PageRequest.of(page,size);

		HashMap<String, Object> data=new HashMap<>(); 

		if (departamento!=null) {
			data.put("departamento",departamento); 
		}
		if (provincia!=null) {
			data.put("provincia",provincia);
		}
		if (departamento!=null) {
			data.put("distrito",distrito);
		}
		if (departamento!=null) {
			data.put("direccion",direccion);
		}
		if (nombre!=null) {
			data.put("nombre",nombre); 
		}
		if (nroDeAlumnos!=null) {
			data.put("nroDeAlumnos",nroDeAlumnos); 
		}
		if (nivel!=null) {
			data.put("nivel",nivel); 
		}
		if (numero!=null) {
			data.put("numero",numero); 
		}

		Page<Institucion> pageInstitucion = InstitucionService.getData(data, pageRequest);

		return pageInstitucion.getContent();
	} 
}
