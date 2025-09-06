package co.analisys.gimnasio.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import co.analisys.gimnasio.model.Equipo;
import co.analisys.gimnasio.service.EquipoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/equipment")
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    @Operation(
        summary = "Agregar un nuevo equipo",
        description = "Este endpoint permite registrar un nuevo equipo en el sistema. " +
        "Debes proporcionar el nombre, descripción y cantidad del equipo. " +
        "Retorna la información del equipo creado."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Equipo agregado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
        @ApiResponse(responseCode = "401", description = "No autorizado"),
        @ApiResponse(responseCode = "403", description = "Prohibido"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Equipo agregarEquipo(@RequestBody Equipo equipo) {
        return equipoService.agregarEquipo(equipo);
    }

    @Operation(
        summary = "Obtener todos los equipos",
        description = "Este endpoint recupera una lista completa de todos los equipos registrados en el sistema. " +
        "No requiere parámetros y retorna los detalles de cada equipo."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de equipos recuperada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
        @ApiResponse(responseCode = "401", description = "No autorizado"),
        @ApiResponse(responseCode = "403", description = "Prohibido"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TRAINER', 'ROLE_MEMBER')")
    public List<Equipo> obtenerTodosEquipos() {
        return equipoService.obtenerTodosEquipos();
    }

    @Operation(
        summary = "Obtener un equipo por ID",
        description = "Este endpoint permite recuperar la información de un equipo específico utilizando su ID. " +
        "Debes proporcionar el ID del equipo en la ruta. Retorna los detalles del equipo si se encuentra."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Equipo recuperado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
        @ApiResponse(responseCode = "401", description = "No autorizado"),
        @ApiResponse(responseCode = "403", description = "Prohibido"),
        @ApiResponse(responseCode = "404", description = "Equipo no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TRAINER', 'ROLE_MEMBER')")
    public ResponseEntity<Equipo> obtenerEquipoPorId(@PathVariable Long id) {
        Equipo equipo = equipoService.obtenerEquipoPorId(id);
        if (equipo != null) {
            return ResponseEntity.ok(equipo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
        summary = "Actualizar un equipo",
        description = "Este endpoint permite actualizar la información de un equipo existente. " +
        "Debes proporcionar el ID del equipo en la ruta y los nuevos datos en el cuerpo de la solicitud. " +
        "Retorna la información del equipo actualizado si se encuentra."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Equipo actualizado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
        @ApiResponse(responseCode = "401", description = "No autorizado"),
        @ApiResponse(responseCode = "403", description = "Prohibido"),
        @ApiResponse(responseCode = "404", description = "Equipo no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Equipo> actualizarEquipo(@PathVariable Long id, @RequestBody Equipo equipo) {
        Equipo actualizado = equipoService.actualizarEquipo(id, equipo);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
        summary = "Eliminar un equipo",
        description = "Este endpoint permite eliminar un equipo existente utilizando su ID. " +
        "Debes proporcionar el ID del equipo en la ruta. Retorna true si la eliminación fue exitosa."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Equipo eliminado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
        @ApiResponse(responseCode = "401", description = "No autorizado"),
        @ApiResponse(responseCode = "403", description = "Prohibido"),
        @ApiResponse(responseCode = "404", description = "Equipo no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Boolean> eliminarEquipo(@PathVariable Long id) {
        boolean eliminado = equipoService.eliminarEquipo(id);
        if (eliminado) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
