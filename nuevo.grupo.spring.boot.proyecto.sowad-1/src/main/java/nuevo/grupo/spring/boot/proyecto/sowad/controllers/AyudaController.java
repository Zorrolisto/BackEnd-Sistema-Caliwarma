package nuevo.grupo.spring.boot.proyecto.sowad.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
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
import nuevo.grupo.spring.boot.proyecto.sowad.models.entity.Ayuda;
import nuevo.grupo.spring.boot.proyecto.sowad.services.IAyudaService;

@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/QW")
public class AyudaController {
	
	private final IAyudaService AyudaService;

	@GetMapping(value="/ayudas" )
	public List<Ayuda> listarAyudas(@RequestParam Map<String,Object> params) {
		int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString())-1) : 0;
		int size = Integer.valueOf(params.get("size").toString());
		
		PageRequest pageRequest = PageRequest.of(page,size);
		
		Page<Ayuda> pageAyuda = AyudaService.getAll(pageRequest);
		 
		return pageAyuda.getContent();
	}
	@GetMapping(value="/ayudas/count" )
	public int cuentaAyudas() {
		return AyudaService.countAyudas();
	}
	
	@GetMapping("/ayudas/{id}")
	public Ayuda show(@PathVariable Long id) {  
		return AyudaService.findByIdAyuda(id);
	}
	
	@PostMapping("/ayudas")
	@ResponseStatus(HttpStatus.CREATED)
	public Ayuda create(@RequestBody Ayuda ayuda) {
		return AyudaService.saveAyuda(ayuda);
	}
	
	@DeleteMapping("/ayudas/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		AyudaService.deleteAyuda(id);
	}
	
	@PutMapping("/ayudas/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Ayuda update(@RequestBody Ayuda ayuda, @PathVariable Long id) {
		Ayuda ayudaActual = AyudaService.findByIdAyuda(id);
		ayudaActual.setFechaDeLlegada(ayuda.getFechaDeLlegada());
		ayudaActual.setFechaDeRegistro(ayuda.getFechaDeRegistro());
		ayudaActual.setInstitucion(ayuda.getInstitucion());
		ayudaActual.setLineasDeAyudas(ayuda.getLineasDeAyudas());
		ayudaActual.setPorcionesTotales(ayuda.getPorcionesTotales());
		ayudaActual.setPrecioTotal(ayuda.getPrecioTotal());
		return AyudaService.saveAyuda(ayudaActual);
	}
	
	//BUSQUEDA PEDIDOS RECORD
	public static final String GREATER_THAN="greater";
	public static final String LESS_THAN="less";
	public static final String EQUAL="equal";
	@GetMapping(value="/ayudas/busqueda")
	public List<Ayuda> listarAyudasPorCriterio(@RequestParam(required=false, name="producto") String producto, 
												@RequestParam(required=false, name="institucion") String institucion,	
												@RequestParam(required=false, name="porcionesTotales") Integer porcionesTotales,
											    @RequestParam(required=false, name="porcionesTotalesCondicion") String porcionesTotalesCondicion,
												@RequestParam(required=false, name="precioTotal") Float precioTotal,
											    @RequestParam(required=false, name="precioTotalCondicion") String precioTotalCondicion,
											    @RequestParam(required=false, name="fechaDeLlegada") @DateTimeFormat(pattern="yyyy-MM-dd") Date fechaDeLlegada,
												@RequestParam(required=false, name="fechaDeLlegadaCondicion") String fechaDeLlegadaCondicion,
												@RequestParam(required=false, name="fechaDeEnvio") @DateTimeFormat(pattern="yyyy-MM-dd") Date fechaDeEnvio,
												@RequestParam(required=false, name="fechaDeEnvioCondicion") String fechaDeEnvioCondicion,
												@RequestParam(required=false, name="fechaDeRegistro") @DateTimeFormat(pattern="yyyy-MM-dd") Date fechaDeRegistro,
												@RequestParam(required=false, name="fechaDeRegistroCondicion") String fechaDeRegistroCondicion,
												@RequestParam Map<String,Object> params) {

		int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString())-1) : 0;
		int size = Integer.valueOf(params.get("size").toString());

		PageRequest pageRequest = PageRequest.of(page,size);

		HashMap<String, Object> data=new HashMap<>(); 
		//producto nombre
		if (producto!=null) {
			data.put("producto",producto); 
		}  
		//institucion codigo o numero
		if (institucion!=null) {
			data.put("institucion",institucion); 
		}
		//porcionesTotales
		if (porcionesTotalesCondicion==null)
			porcionesTotalesCondicion=LESS_THAN;
		if (!porcionesTotalesCondicion.equals(GREATER_THAN) && 	!porcionesTotalesCondicion.equals(LESS_THAN) && !porcionesTotalesCondicion.equals(EQUAL))
			porcionesTotalesCondicion=GREATER_THAN;
		if (porcionesTotales!=null){
			data.put("porcionesTotales",porcionesTotales);
			data.put("porcionesTotalesCondicion", porcionesTotalesCondicion);
		}
		//precioTotal
		if (precioTotalCondicion==null)
			precioTotalCondicion=LESS_THAN;
		if (!precioTotalCondicion.equals(GREATER_THAN) && 	!precioTotalCondicion.equals(LESS_THAN) && !precioTotalCondicion.equals(EQUAL))
			precioTotalCondicion=GREATER_THAN;
		if (precioTotal!=null){
			data.put("precioTotal",precioTotal);
			data.put("precioTotalCondicion", precioTotalCondicion);
		}
		//fechaDeLlegada
		if (fechaDeLlegadaCondicion==null)
			fechaDeLlegadaCondicion=LESS_THAN;
		if (!fechaDeLlegadaCondicion.equals(GREATER_THAN) && 	!fechaDeLlegadaCondicion.equals(LESS_THAN) && !fechaDeLlegadaCondicion.equals(EQUAL))
			fechaDeLlegadaCondicion=GREATER_THAN;	
		if (fechaDeLlegada!=null){
			data.put("fechaDeLlegada",fechaDeLlegada); 
			data.put("fechaDeLlegadaCondicion", fechaDeLlegadaCondicion);
		} 
		//fechaDeEnvio
		if (fechaDeEnvioCondicion==null)
			fechaDeEnvioCondicion=LESS_THAN;
		if (!fechaDeEnvioCondicion.equals(GREATER_THAN) && 	!fechaDeEnvioCondicion.equals(LESS_THAN) && !fechaDeEnvioCondicion.equals(EQUAL))
			fechaDeEnvioCondicion=GREATER_THAN;	
		if (fechaDeEnvio!=null){
			data.put("fechaDeEnvio",fechaDeEnvio); 
			data.put("fechaDeEnvioCondicion", fechaDeEnvioCondicion);
		}
		//fechaDeRegistro
		if (fechaDeRegistroCondicion==null)
			fechaDeRegistroCondicion=LESS_THAN;
		if (!fechaDeRegistroCondicion.equals(GREATER_THAN) && 	!fechaDeRegistroCondicion.equals(LESS_THAN) && !fechaDeRegistroCondicion.equals(EQUAL))
			fechaDeRegistroCondicion=GREATER_THAN;	
		if (fechaDeRegistro!=null){
			data.put("fechaDeRegistro",fechaDeRegistro); 
			data.put("fechaDeRegistroCondicion", fechaDeRegistroCondicion);
		}

		Page<Ayuda> pageAyuda = AyudaService.getData(data, pageRequest);

		return pageAyuda.getContent();
	} 
}
