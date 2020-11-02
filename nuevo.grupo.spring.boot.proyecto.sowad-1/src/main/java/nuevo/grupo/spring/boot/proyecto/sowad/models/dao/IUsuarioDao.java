package nuevo.grupo.spring.boot.proyecto.sowad.models.dao;

import nuevo.grupo.spring.boot.proyecto.sowad.models.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioDao extends JpaRepository<Usuario, Long> {
    public Usuario findByUsername(String username);
}
