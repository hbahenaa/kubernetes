package org.trabajo.springcloud.msvc.cursos.services;

import org.trabajo.springcloud.msvc.cursos.entity.Curso;

import java.util.List;
import java.util.Optional;

public interface ICursoService {
    List<Curso> all();
    Optional<Curso> findId(Long id);

    Curso add(Curso curso);

    void delete(Long id);
}
