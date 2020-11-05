package nuevo.grupo.spring.boot.proyecto.sowad;

import nuevo.grupo.spring.boot.proyecto.sowad.models.entity.*;
import nuevo.grupo.spring.boot.proyecto.sowad.services.AyudaServiceImpl;
import nuevo.grupo.spring.boot.proyecto.sowad.services.InstitucionServiceImpl;
import nuevo.grupo.spring.boot.proyecto.sowad.services.ProductoServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private ProductoServiceImpl productoService;

	@Test
	void saveProducto_numerosEnNombre_ReturnException() {
		Producto instance = new Producto();
		instance.setNombre("234234");
		instance.setPorcionPorPersona(10f);
		instance.setTipo("234234");
		instance.setPrecio(4234);
		instance.setStock(23434);
		instance.setMarca("234234");
		try{
			productoService.saveProducto(instance);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.isNull(productoService.findByNombre(instance.getNombre()), "No se guardo el id");
	}
	@Test
	void saveProducto_caracteresEspeciales_ReturnException() {
		Producto instance = new Producto();
		instance.setNombre("&/(/#$%#");
		instance.setPorcionPorPersona(10f);
		instance.setTipo("#$%#");
		instance.setPrecio(4234);
		instance.setStock(23234);
		instance.setMarca("#$%#");
		try{
			productoService.saveProducto(instance);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.isNull(productoService.findByNombre(instance.getNombre()), "No se guardo el id");
	}
	@Test
	void saveProducto_caracteresString_Succesfull() {
		Producto instance = new Producto();
		instance.setNombre("Producto");
		instance.setPorcionPorPersona(10f);
		instance.setTipo("Tipo");
		instance.setPrecio(4234);
		instance.setStock(34234);
		instance.setMarca("Marca");
		try{
			productoService.saveProducto(instance);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.notNull(productoService.findByNombre(instance.getNombre()), "No se guardo el id");
	}

	@Autowired
	private InstitucionServiceImpl institucionService;

	@Test
	void saveInstitucion_numeros_ReturnException() {
		var cantidadInstituciones = institucionService.countInstituciones();
		Institucion instance = new Institucion();
		Lugar lugar = new Lugar();
		lugar.setDepartamento("Departamento");
		lugar.setProvincia("Provincia");
		lugar.setDistrito("Distrito");
		instance.setLugar(lugar);
		instance.setNivel("123123");
		instance.setNombre("234234");
		instance.setNroDeAlumnos(123);
		instance.setNumero("123123");
		try{
			institucionService.saveInstitucion(instance);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.isTrue(cantidadInstituciones==institucionService.countInstituciones(), "No se guardo"+institucionService.countInstituciones());
	}
	@Test
	void saveInstitucion_caracteresEspeciales_ReturnException() {
		var cantidadInstituciones = institucionService.countInstituciones();
		Institucion instance = new Institucion();
		Lugar lugar = new Lugar();
		lugar.setDepartamento("Departamento");
		lugar.setProvincia("Provincia");
		lugar.setDistrito("Distrito");
		instance.setLugar(lugar);
		instance.setNivel("123123");
		instance.setNombre("234234");
		instance.setNroDeAlumnos(123);
		instance.setNumero("123123");
		try{
			institucionService.saveInstitucion(instance);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.isTrue(cantidadInstituciones==institucionService.countInstituciones(), "No se guardo"+institucionService.countInstituciones());
	}

	@Test
	void saveInstitucion_caracteresString_Succesfull() {
		var cantidadInstituciones = institucionService.countInstituciones();
		Institucion instance = new Institucion();
		Lugar lugar = new Lugar();
		lugar.setDepartamento("Departamento");
		lugar.setProvincia("Provincia");
		lugar.setDistrito("Distrito");
		instance.setLugar(lugar);
		instance.setNivel("nivel");
		instance.setNombre("Institucion");
		instance.setNroDeAlumnos(123);
		instance.setNumero("2524");
		try{
			institucionService.saveInstitucion(instance);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.isTrue(cantidadInstituciones<institucionService.countInstituciones(), "No se guardo"+institucionService.countInstituciones());
	}

	@Test
	void saveInstitucion_lugarConNumeros_ReturnException() {
		var cantidadInstituciones = institucionService.countInstituciones();
		Institucion instance = new Institucion();
		Lugar lugar = new Lugar();
		lugar.setDepartamento("123213");
		lugar.setProvincia("123123");
		lugar.setDistrito("123123");
		instance.setLugar(lugar);
		instance.setNivel("nivel");
		instance.setNombre("Institucion");
		instance.setNroDeAlumnos(123);
		instance.setNumero("2524");
		try{
			institucionService.saveInstitucion(instance);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.isTrue(cantidadInstituciones==institucionService.countInstituciones(), "No se guardo"+institucionService.countInstituciones());
	}
	@Test
	void saveInstitucion_lugarConCaracteresEspeciales_ReturnException() {
		var cantidadInstituciones = institucionService.countInstituciones();
		Institucion instance = new Institucion();
		Lugar lugar = new Lugar();
		lugar.setDepartamento("#$&#$%");
		lugar.setProvincia("$#W%#$");
		lugar.setDistrito("$%$%&");
		instance.setLugar(lugar);
		instance.setNivel("nivel");
		instance.setNombre("Institucion");
		instance.setNroDeAlumnos(123);
		instance.setNumero("123123");
		try{
			institucionService.saveInstitucion(instance);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.isTrue(cantidadInstituciones==institucionService.countInstituciones(), "No se guardo"+institucionService.countInstituciones());
	}

	@Test
	void saveInstitucion_lugarConCaracteresLetras_Succesfull() {
		var cantidadInstituciones = institucionService.countInstituciones();
		Institucion instance = new Institucion();
		Lugar lugar = new Lugar();
		lugar.setDepartamento("Departamento");
		lugar.setProvincia("Provincia");
		lugar.setDistrito("Distrito");
		instance.setLugar(lugar);
		instance.setNivel("nivel");
		instance.setNombre("Institucion");
		instance.setNroDeAlumnos(123);
		instance.setNumero("61223");
		try{
			institucionService.saveInstitucion(instance);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.isTrue(cantidadInstituciones<institucionService.countInstituciones(), "No se guardo"+institucionService.countInstituciones());
	}

	@Autowired
	private AyudaServiceImpl ayudaService;

	@Test
	void saveAyuda_mayorQue100000PorcionesTotales_ReturnException() {
		var cantidadAyudas = ayudaService.countAyudas();
		Ayuda instance = new Ayuda();
		instance.setFechaDeEnvio(new Date());
		instance.setFechaDeLlegada(new Date());
		instance.setFechaDeRegistro(new Date());
		instance.setInstitucion(institucionService.findByIdInstitucion((long)1));
		Set<LineaDeAyuda> lineas = new HashSet<LineaDeAyuda>();
		lineas.add(new LineaDeAyuda());		instance.setLineasDeAyudas(lineas);
		instance.setPorcionesTotales(100001);
		instance.setPrecioTotal(123f);
		try{
			ayudaService.saveAyuda(instance);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.isTrue(cantidadAyudas==ayudaService.countAyudas(), "No se guardo"+ayudaService.countAyudas());
	}
	@Test
	void saveAyuda_menorA1PrecioTotal_ReturnException() {
		var cantidadAyudas = ayudaService.countAyudas();
		Ayuda instance = new Ayuda();
		instance.setFechaDeEnvio(new Date());
		instance.setFechaDeLlegada(new Date());
		instance.setFechaDeRegistro(new Date());
		instance.setInstitucion(institucionService.findByIdInstitucion((long)1));
		Set<LineaDeAyuda> lineas = new HashSet<LineaDeAyuda>();
		lineas.add(new LineaDeAyuda());		instance.setLineasDeAyudas(lineas);
		instance.setPorcionesTotales(100);
		instance.setPrecioTotal(0f);
		try{
			ayudaService.saveAyuda(instance);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.isTrue(cantidadAyudas==ayudaService.countAyudas(), "No se guardo"+ayudaService.countAyudas());
	}
	@Test
	void saveAyuda_Succesfull() {
		var cantidadAyudas = ayudaService.countAyudas();
		Ayuda instance = new Ayuda();
		instance.setFechaDeEnvio(new Date());
		instance.setFechaDeLlegada(new Date());
		instance.setFechaDeRegistro(new Date());
		instance.setInstitucion(institucionService.findByIdInstitucion((long)1));
		Set<LineaDeAyuda> lineas = new HashSet<LineaDeAyuda>();
		lineas.add(new LineaDeAyuda());
		instance.setLineasDeAyudas(lineas);
		instance.setPorcionesTotales(100);
		instance.setPrecioTotal(123f);
		try{
			ayudaService.saveAyuda(instance);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.isTrue(cantidadAyudas<ayudaService.countAyudas(), "No se guardo"+ayudaService.countAyudas());
	}
}

