package br.com.fiap.SkyAid.controller;

import br.com.fiap.SkyAid.dto.request.RegistroSensorRequestDto;
import br.com.fiap.SkyAid.dto.response.RegistroSensorResponseDto;
import br.com.fiap.SkyAid.model.Drone;
import br.com.fiap.SkyAid.model.RegistroSensor;
import br.com.fiap.SkyAid.service.DroneService;
import br.com.fiap.SkyAid.service.RegistroSensorService;
import br.com.fiap.SkyAid.util.TipoSensor;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sensores")
public class RegistroSensorController {

    @Autowired
    private RegistroSensorService sensorService;

    @Autowired
    private DroneService droneService;

    // GET /api/sensores
    @GetMapping
    public ResponseEntity<List<RegistroSensorResponseDto>> listarTodos() {
        List<RegistroSensorResponseDto> lista = sensorService.listarTodosRegistros()
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }

    // GET /api/sensores/{id}
    @GetMapping("/{id}")
    public ResponseEntity<RegistroSensorResponseDto> buscarPorId(@PathVariable Long id) {
        return sensorService.buscarRegistroPorId(id)
                .map(this::toResponseDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/sensores/tipo/{tipo}
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<RegistroSensorResponseDto>> buscarPorTipo(@PathVariable TipoSensor tipo) {
        List<RegistroSensorResponseDto> lista = sensorService.buscarRegistrosPorTipo(tipo)
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }

    // POST /api/sensores
    @PostMapping
    public ResponseEntity<RegistroSensorResponseDto> criar(@RequestBody @Valid RegistroSensorRequestDto dto) {
        Optional<Drone> droneOpt = droneService.buscarDronePorId(dto.getDroneId());
        if (droneOpt.isEmpty()) return ResponseEntity.badRequest().build();

        RegistroSensor sensor = new RegistroSensor();
        sensor.setTipo(dto.getTipo());
        sensor.setValor(dto.getValor());
        sensor.setDataHora(dto.getDataHora());
        sensor.setLatitude(dto.getLatitude());
        sensor.setLongitude(dto.getLongitude());
        sensor.setDrone(droneOpt.get());

        RegistroSensor salvo = sensorService.criarRegistroSensor(sensor);
        return ResponseEntity.ok(toResponseDto(salvo));
    }

    // PUT /api/sensores/{id}
    @PutMapping("/{id}")
    public ResponseEntity<RegistroSensorResponseDto> atualizar(@PathVariable Long id,
                                                               @RequestBody @Valid RegistroSensorRequestDto dto) {
        Optional<Drone> droneOpt = droneService.buscarDronePorId(dto.getDroneId());
        if (droneOpt.isEmpty()) return ResponseEntity.badRequest().build();

        RegistroSensor sensor = new RegistroSensor();
        sensor.setTipo(dto.getTipo());
        sensor.setValor(dto.getValor());
        sensor.setDataHora(dto.getDataHora());
        sensor.setLatitude(dto.getLatitude());
        sensor.setLongitude(dto.getLongitude());
        sensor.setDrone(droneOpt.get());

        RegistroSensor atualizado = sensorService.atualizarRegistroSensor(id, sensor);
        return ResponseEntity.ok(toResponseDto(atualizado));
    }

    // DELETE /api/sensores/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        sensorService.deletarRegistroSensor(id);
        return ResponseEntity.noContent().build();
    }

    // Conversão entidade → DTO
    private RegistroSensorResponseDto toResponseDto(RegistroSensor sensor) {
        RegistroSensorResponseDto dto = new RegistroSensorResponseDto();
        dto.setId(sensor.getId());
        dto.setTipo(sensor.getTipo());
        dto.setValor(sensor.getValor());
        dto.setDataHora(sensor.getDataHora());
        dto.setLatitude(sensor.getLatitude());
        dto.setLongitude(sensor.getLongitude());
        dto.setDroneId(sensor.getDrone().getId());
        dto.setNomeDrone(sensor.getDrone().getIdentificador());
        return dto;
    }
}
