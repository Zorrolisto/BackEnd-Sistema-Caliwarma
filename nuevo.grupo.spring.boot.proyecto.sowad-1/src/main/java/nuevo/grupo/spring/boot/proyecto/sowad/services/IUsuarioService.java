package nuevo.grupo.spring.boot.proyecto.sowad.services;

import nuevo.grupo.spring.boot.proyecto.sowad.models.entity.Usuario;

public interface IUsuarioService {
    public Usuario findByUsername(String username);
}
