package org.trabajo.springcloud.msvc.usuarios.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.trabajo.springcloud.msvc.usuarios.models.entity.Usuario;
import org.trabajo.springcloud.msvc.usuarios.service.IUsuarioService;

import java.util.*;

@RestController()
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping
    public List<Usuario> findAll(){
        return usuarioService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAll(@PathVariable(name = "id") Long id){
        Optional<Usuario> usuarioOptinal = usuarioService.findId(id);
        if(usuarioOptinal.isPresent()) {
            return ResponseEntity.ok(usuarioOptinal.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addUsuario(@Valid @RequestBody Usuario usuario, BindingResult result){
        if(usuarioService.findEmail(usuario.getEmail()).isPresent()){
            return getMapResponseEntity();
        }

        if(result.hasErrors()){
            return getMapResponseEntity(result);
        }


        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.add(usuario));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateUsuario(@RequestBody Usuario usuario, BindingResult result, @PathVariable Long id){
        Optional<Usuario> usuarioOptinal = usuarioService.findId(id);
        if(result.hasErrors()){
            return getMapResponseEntity(result);
        }
        if(usuarioOptinal.isPresent()) {
            Usuario usuarioDB= usuarioOptinal.get();
            if(usuario.getEmail().equalsIgnoreCase(usuarioDB.getEmail()) && usuarioService.findEmail(usuario.getEmail()).isPresent()){
                return getMapResponseEntity();
            }
            usuarioDB.setNombre(usuario.getNombre());
            usuarioDB.setEmail(usuario.getEmail());
            usuarioDB.setPassword(usuario.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.add(usuarioDB));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@Valid @PathVariable Long id) {
        Optional<Usuario> usuarioOptinal = usuarioService.findId(id);
        if (usuarioOptinal.isPresent()){
            usuarioService.delete(id);
            return ResponseEntity.ok().build();
     }
        return ResponseEntity.notFound().build();
    }

    private static ResponseEntity<Map<String, String>> getMapResponseEntity(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err ->{
            errores.put(err.getField(), "El campo " + err.getField()+ " "+ err.getDefaultMessage());
        } );
        return ResponseEntity.badRequest().body(errores);
    }
    private static ResponseEntity<Map<String, String>> getMapResponseEntity() {
        return ResponseEntity.badRequest().
                body(Collections.singletonMap("mensaje", "ya exite el correo registrado con un usuario"));
    }

}
