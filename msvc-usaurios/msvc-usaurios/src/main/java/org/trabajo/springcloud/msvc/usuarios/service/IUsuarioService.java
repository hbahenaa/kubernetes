package org.trabajo.springcloud.msvc.usuarios.service;

import org.trabajo.springcloud.msvc.usuarios.models.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {


    List<Usuario> listar();

    Optional<Usuario> findId(Long id);

    Usuario add(Usuario usuario);

    void delete(Long id);

    Optional<Usuario> findEmail (String email);
}
