package nuevo.grupo.spring.boot.proyecto.sowad.services;

import nuevo.grupo.spring.boot.proyecto.sowad.models.entity.LineaDeAyuda;

public interface ILineaDeAyudaService {
	public LineaDeAyuda findByIdLineaDeAyuda(Long idLineaDeAyuda);
	public LineaDeAyuda saveLineaDeAyuda(LineaDeAyuda lineaDeAyuda); 
	public void deleteLineaDeAyuda(Long idLineaDeAyuda);
}
