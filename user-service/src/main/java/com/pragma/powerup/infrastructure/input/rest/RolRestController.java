package com.pragma.powerup.infrastructure.input.rest;


import com.pragma.powerup.application.dto.request.RolRequestDTO;
import com.pragma.powerup.application.dto.response.RolResponseDTO;
import com.pragma.powerup.application.handler.IRolHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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


    @Operation(summary = "Add a new rol")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Rol created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Rol already exists", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<Void> saveRol(@Valid @RequestBody RolRequestDTO rolRequestDto){
        rolHandler.saveRol(rolRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @Operation(summary = "Get all roles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All roles returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = RolResponseDTO.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/")
    public ResponseEntity<List<RolResponseDTO>> getAllRoles(){
        return ResponseEntity.ok(rolHandler.getAllRol());
    }

    @Operation(summary = "Get Rol By Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rol returned", content = @Content),
            @ApiResponse(responseCode = "409", description = "Rol already exists", content = @Content)
    })

    @GetMapping("/{id}")
    public ResponseEntity<RolResponseDTO> getRolById(@PathVariable(value = "id") Long rolId){
        return ResponseEntity.ok(rolHandler.getRolById(rolId));
    }

    @Operation(summary = "Delete Rol By Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Rol eliminated", content = @Content),
            @ApiResponse(responseCode = "409", description = "Rol already exists", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRolByid(@PathVariable(value = "id")Long rolId){
        rolHandler.deleteRolByid(rolId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
