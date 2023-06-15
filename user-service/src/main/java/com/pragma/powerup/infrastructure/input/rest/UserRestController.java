package com.pragma.powerup.infrastructure.input.rest;


import com.pragma.powerup.application.dto.request.UserRequestDTO;
import com.pragma.powerup.application.dto.response.RolResponseDTO;
import com.pragma.powerup.application.dto.response.UserResponseDTO;
import com.pragma.powerup.application.handler.IUserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserRestController {

    private final IUserHandler userHandler;
    @Operation(summary = "Add a new Administrador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = " Admin created", content = @Content),
            @ApiResponse(responseCode = "409", description = " Admin already exists", content = @Content)
    })

    @PostMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> saveAdmin(@Valid @RequestBody UserRequestDTO propietario){
        userHandler.saveUser(propietario);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Add a new empleado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Empleado created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Empleado already exists", content = @Content)
    })
    @PostMapping("/empleado")
    @PreAuthorize("hasAuthority('PROPIETARIO')")
    public ResponseEntity<Void> saveEmpleado(@Valid @RequestBody UserRequestDTO empleado){
        userHandler.saveUser(empleado);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Add a new cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "cliente created", content = @Content),
            @ApiResponse(responseCode = "409", description = "cliente already exists", content = @Content)
    })
    @PostMapping("/cliente")
    public ResponseEntity<Void> saveCliente(@Valid @RequestBody UserRequestDTO cliente){
        userHandler.saveUser(cliente);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @Operation(summary = "Add a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "user created", content = @Content),
            @ApiResponse(responseCode = "409", description = "user already exists", content = @Content)
    })

    @PostMapping("/User")
    public ResponseEntity<Void> saveUser(@Valid @RequestBody UserRequestDTO userRequestDTO){
        userHandler.saveUser(userRequestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All users returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UserResponseDTO.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })

    @GetMapping("/Users")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        return ResponseEntity.ok(userHandler.getAllUsers());
    }

    @Operation(summary = "Get a user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User returned", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable(value = "id") Long userId){
        return ResponseEntity.ok(userHandler.getUserById(userId));
    }

    @Operation(summary = "Get a user by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User returned", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDTO> getUserByCorreo(@PathVariable(value = "email") String correo){
        return  ResponseEntity.ok((userHandler.getUserByCorreo(correo)));
    }



    @Operation(summary = "Detele a user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deteteUserById(@PathVariable(value = "id")Long usuarioId){
        userHandler.deleteUserById(usuarioId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
