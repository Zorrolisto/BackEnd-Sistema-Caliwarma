package nuevo.grupo.spring.boot.proyecto.sowad.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import nuevo.grupo.spring.boot.proyecto.sowad.models.dao.ILineaDeAyudaDao;
import nuevo.grupo.spring.boot.proyecto.sowad.models.entity.LineaDeAyuda;

@RequiredArgsConstructor
@Service
public class LineaDeAyudaServiceImpl implements ILineaDeAyudaService {
    
	private final ILineaDeAyudaDao LineaDeAyudaDao; 

	@Transactional(readOnly=true)
	@Override
	public LineaDeAyuda findByIdLineaDeAyuda(Long idLineaDeAyuda) {
		return LineaDeAyudaDao.findById(idLineaDeAyuda).orElse(null);
	}

	@Transactional
	@Override
	public LineaDeAyuda saveLineaDeAyuda(LineaDeAyuda lineaDeAyuda) {
		return LineaDeAyudaDao.save(lineaDeAyuda);
	}

	@Transactional
	@Override
	public void deleteLineaDeAyuda(Long idLineaDeAyuda) {
		LineaDeAyudaDao.deleteById(idLineaDeAyuda);
	}

}
