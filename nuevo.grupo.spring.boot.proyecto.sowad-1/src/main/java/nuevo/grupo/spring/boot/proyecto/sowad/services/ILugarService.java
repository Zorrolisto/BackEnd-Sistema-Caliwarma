package nuevo.grupo.spring.boot.proyecto.sowad.services;

import java.util.HashMap;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import nuevo.grupo.spring.boot.proyecto.sowad.models.entity.Lugar;

public interface ILugarService {
	public Lugar findByIdLugar(Long idLugar);
	public Lugar saveLugar(Lugar lugar); 
	public void deleteLugar(Long idLugar);
	public Page<Lugar> getAll(Pageable pageable);
	public Page<Lugar> getData(HashMap<String, Object> conditions, Pageable pageable);
	public int countLugares();
}
