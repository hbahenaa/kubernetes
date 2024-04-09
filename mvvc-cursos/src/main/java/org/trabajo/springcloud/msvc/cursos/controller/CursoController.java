package org.trabajo.springcloud.msvc.cursos.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.trabajo.springcloud.msvc.cursos.entity.Curso;
import org.trabajo.springcloud.msvc.cursos.services.ICursoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController()
public class CursoController {

    @Autowired
    private ICursoService cursoService;


    @GetMapping
    public ResponseEntity<List<Curso>> findAll(){
        return ResponseEntity.ok(cursoService.all());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> findId(@PathVariable Long id){
        Optional<Curso> oCurso =cursoService.findId(id);
        if(oCurso.isPresent()){
            return ResponseEntity.ok(oCurso.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addCurso(@Valid @RequestBody Curso curso, BindingResult result){


        if(result.hasErrors()){
            return getMapResponseEntity(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.add(curso));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateUsuario(@Valid @RequestBody Curso curso, BindingResult result, @PathVariable Long id){
        Optional<Curso> cursoO = cursoService.findId(id);
        if(result.hasErrors()){
            return getMapResponseEntity(result);
        }
        if(cursoO.isPresent()) {
            Curso cursoDB= cursoO.get();
            cursoDB.setNombre(curso.getNombre());
            return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.add(cursoDB));
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCurso( @PathVariable Long id) {
        Optional<Curso> cursoOptinal = cursoService.findId(id);
        if (cursoOptinal.isPresent()){
            cursoService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    private static ResponseEntity<Map<String, String>> getMapResponseEntity(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err ->{
            errores.put(err.getField(), "El campo" + err.getField()+ " "+ err.getDefaultMessage());
        } );
        return ResponseEntity.badRequest().body(errores);
    }
}
