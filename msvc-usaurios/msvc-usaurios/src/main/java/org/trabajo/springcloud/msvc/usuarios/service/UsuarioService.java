package org.trabajo.springcloud.msvc.usuarios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.trabajo.springcloud.msvc.usuarios.models.entity.Usuario;
import org.trabajo.springcloud.msvc.usuarios.repositories.UsuarioRepository;

import java.util.List;
import java.util.Optional;


@Service
public class UsuarioService implements IUsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public List<Usuario> listar() {
        return (List<Usuario>) usuarioRepository.findAll();
    }


    @Transactional(readOnly = true)
    public Optional<Usuario> findId(Long id) {
        return usuarioRepository.findById(id);
    }



    @Transactional
    public Usuario add(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }


    @Transactional
    public void delete(Long id) {
         usuarioRepository.deleteById(id);
    }

    @Override
    public Optional<Usuario> findEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}
