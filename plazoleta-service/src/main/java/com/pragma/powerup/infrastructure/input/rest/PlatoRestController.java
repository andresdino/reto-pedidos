package com.pragma.powerup.infrastructure.input.rest;


import com.pragma.powerup.application.dto.request.PlatoRequestDTO;
import com.pragma.powerup.application.dto.request.PlatoRequestPutDTO;
import com.pragma.powerup.application.dto.response.PlatoResponseDTO;
import com.pragma.powerup.application.handler.IPlatoHandler;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/plato")
@RequiredArgsConstructor
public class PlatoRestController {

    private final IPlatoHandler platoHandler;

    @PostMapping("/save")
    public ResponseEntity<Void> savePlato(@Valid @RequestBody PlatoRequestDTO platoRequestDTO){
        platoHandler.savePlato(platoRequestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<PlatoRequestDTO> PutPlato (@PathVariable (value = "id") Long platoId, @Valid @RequestBody PlatoRequestPutDTO platoRequestPutDTO) {
        platoHandler.putPlato(platoId ,platoRequestPutDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*@PutMapping("/{id}")
    public ResponseEntity<PlatoRequestDTO> putEnableDisablePlato(@PathVariable (value = "id") Long platoId, @PathVariable(value = "enableDisable") Long enableDisable){
        platoHandler.putEnableDiseablePlato(platoId, enableDisable);
        return new ResponseEntity<>(HttpStatus.OK);
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<PlatoResponseDTO> getPlatoId(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(platoHandler.getPlatoById(id));
    }

    @GetMapping("/get")
    public ResponseEntity<List<PlatoResponseDTO>> getAllPlato(){
        return ResponseEntity.ok(platoHandler.getAllPlatos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlatoById(@PathVariable(value="id") Long id){
        platoHandler.deletePlatoById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
