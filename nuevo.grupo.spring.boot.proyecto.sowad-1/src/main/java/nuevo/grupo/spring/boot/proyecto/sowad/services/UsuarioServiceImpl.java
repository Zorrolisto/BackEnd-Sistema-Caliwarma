package nuevo.grupo.spring.boot.proyecto.sowad.services;

import lombok.RequiredArgsConstructor;
import nuevo.grupo.spring.boot.proyecto.sowad.models.dao.IUsuarioDao;
import nuevo.grupo.spring.boot.proyecto.sowad.models.entity.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UsuarioServiceImpl  implements IUsuarioService{
    private final IUsuarioDao usuarioDao;

    @Transactional(readOnly = true)
    public Usuario findByUsername(String username) {
        return usuarioDao.findByUsername(username);
    }
}
