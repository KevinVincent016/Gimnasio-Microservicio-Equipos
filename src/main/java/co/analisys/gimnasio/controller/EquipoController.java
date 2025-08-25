package co.analisys.gimnasio.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import co.analisys.gimnasio.model.Equipo;
import co.analisys.gimnasio.service.EquipoService;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/equipment")
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping
    public Equipo agregarEquipo(@RequestBody Equipo equipo) {
        return equipoService.agregarEquipo(equipo);
    }

    @GetMapping
    public List<Equipo> obtenerTodosEquipos() {
        return equipoService.obtenerTodosEquipos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipo> obtenerEquipoPorId(@PathVariable Long id) {
        Equipo equipo = equipoService.obtenerEquipoPorId(id);
        if (equipo != null) {
            return ResponseEntity.ok(equipo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipo> actualizarEquipo(@PathVariable Long id, @RequestBody Equipo equipo) {
        Equipo actualizado = equipoService.actualizarEquipo(id, equipo);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminarEquipo(@PathVariable Long id) {
        boolean eliminado = equipoService.eliminarEquipo(id);
        if (eliminado) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/classes")
    public ResponseEntity<Object> obtenerClasesDeEquipo(@PathVariable Long id) {
        String url = "http://localhost:8082/api/classes?equipoId=" + id;
        Object clases = restTemplate.getForObject(url, Object.class);
        return ResponseEntity.ok(clases);
    }
}
