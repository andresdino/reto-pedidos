package com.pragma.powerup.infrastructure.input.rest;


import com.pragma.powerup.application.dto.request.RolRequestDTO;
import com.pragma.powerup.application.dto.response.RolResponseDTO;
import com.pragma.powerup.application.handler.IRolHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/rol")
@RequiredArgsConstructor
public class RolRestController {

    private final IRolHandler rolHandler;

    @PostMapping("/")
    public ResponseEntity<Void> guardarRol(@Valid @RequestBody RolRequestDTO rolRequestDto){
        rolHandler.saveRol(rolRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<RolResponseDTO>> obtenerTodosRoles(){
        return ResponseEntity.ok(rolHandler.getAllRol());
    }


}
