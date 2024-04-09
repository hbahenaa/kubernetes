package org.trabajo.springcloud.msvc.cursos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.trabajo.springcloud.msvc.cursos.entity.Curso;
import org.trabajo.springcloud.msvc.cursos.repositories.CursoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService implements ICursoService{

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Curso> all() {
            return (List<Curso>) cursoRepository.findAll();

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> findId(Long id) {

        return cursoRepository.findById(id);
    }

    @Override
    @Transactional
    public Curso add(Curso curso) {

        return cursoRepository.save(curso);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        cursoRepository.deleteById(id);
    }
}
