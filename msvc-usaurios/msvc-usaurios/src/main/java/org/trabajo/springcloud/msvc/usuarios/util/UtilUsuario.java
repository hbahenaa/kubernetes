package org.trabajo.springcloud.msvc.usuarios.util;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

public class UtilUsuario {
    private static ResponseEntity<Map<String, String>> getMapResponseEntity(BindingResult result) {
        if(result.hasErrors()){
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(err ->{
                errores.put(err.getField(), "El campo" + err.getField()+ " "+ err.getDefaultMessage());
            } );
            return ResponseEntity.badRequest().body(errores);
        }
        return null;
    }
}
