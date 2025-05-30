package br.com.fiap.SkyAid.controller;

import br.com.fiap.SkyAid.dto.request.DroneRequestDto;
import br.com.fiap.SkyAid.dto.response.DroneResponseDto;
import br.com.fiap.SkyAid.model.Drone;
import br.com.fiap.SkyAid.model.Operador;
import br.com.fiap.SkyAid.service.DroneService;
import br.com.fiap.SkyAid.service.OperadorService;
import br.com.fiap.SkyAid.util.StatusDrone;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/drones")
public class DroneController {

    @Autowired
    private DroneService droneService;

    @Autowired
    private OperadorService operadorService;

    // GET /api/drones
    @GetMapping
    public ResponseEntity<List<DroneResponseDto>> listarTodos() {
        List<DroneResponseDto> drones = droneService.listarTodosDrones()
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(drones);
    }

    // GET /api/drones/{id}
    @GetMapping("/{id}")
    public ResponseEntity<DroneResponseDto> buscarPorId(@PathVariable Long id) {
        return droneService.buscarDronePorId(id)
                .map(this::toResponseDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/drones/status/{status}
    @GetMapping("/status/{status}")
    public ResponseEntity<List<DroneResponseDto>> buscarPorStatus(@PathVariable StatusDrone status) {
        List<DroneResponseDto> drones = droneService.buscarDronesPorStatus(status)
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(drones);
    }

    // POST /api/drones
    @PostMapping
    public ResponseEntity<DroneResponseDto> criar(@RequestBody @Valid DroneRequestDto dto) {
        Optional<Operador> operadorOpt = operadorService.buscarOperadorPorId(dto.getOperadorId());
        if (operadorOpt.isEmpty()) return ResponseEntity.badRequest().build();

        Drone drone = new Drone();
        drone.setIdentificador(dto.getIdentificador());
        drone.setModelo(dto.getModelo());
        drone.setStatus(dto.getStatus());
        drone.setOperador(operadorOpt.get());

        Drone salvo = droneService.criarDrone(drone);
        return ResponseEntity.ok(toResponseDto(salvo));
    }

    // PUT /api/drones/{id}
    @PutMapping("/{id}")
    public ResponseEntity<DroneResponseDto> atualizar(@PathVariable Long id, @RequestBody @Valid DroneRequestDto dto) {
        Optional<Operador> operadorOpt = operadorService.buscarOperadorPorId(dto.getOperadorId());
        if (operadorOpt.isEmpty()) return ResponseEntity.badRequest().build();

        Drone drone = new Drone();
        drone.setIdentificador(dto.getIdentificador());
        drone.setModelo(dto.getModelo());
        drone.setStatus(dto.getStatus());
        drone.setOperador(operadorOpt.get());

        Drone atualizado = droneService.atualizarDrone(id, drone);
        return ResponseEntity.ok(toResponseDto(atualizado));
    }

    // DELETE /api/drones/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        droneService.deletarDrone(id);
        return ResponseEntity.noContent().build();
    }

    // Método auxiliar para conversão
    private DroneResponseDto toResponseDto(Drone drone) {
        DroneResponseDto dto = new DroneResponseDto();
        dto.setId(drone.getId());
        dto.setIdentificador(drone.getIdentificador());
        dto.setModelo(drone.getModelo());
        dto.setStatus(drone.getStatus());
        dto.setOperadorId(drone.getOperador().getId());
        dto.setNomeOperador(drone.getOperador().getNome());
        return dto;
    }
}
