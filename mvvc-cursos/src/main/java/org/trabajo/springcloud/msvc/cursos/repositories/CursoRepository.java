package org.trabajo.springcloud.msvc.cursos.repositories;

import org.springframework.data.repository.CrudRepository;
import org.trabajo.springcloud.msvc.cursos.entity.Curso;

public interface CursoRepository extends CrudRepository<Curso, Long> {
}
