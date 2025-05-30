package br.com.fiap.SkyAid.controller;

import br.com.fiap.SkyAid.dto.request.MissaoRequestDto;
import br.com.fiap.SkyAid.dto.response.MissaoResponseDto;
import br.com.fiap.SkyAid.model.Drone;
import br.com.fiap.SkyAid.model.Missao;
import br.com.fiap.SkyAid.service.DroneService;
import br.com.fiap.SkyAid.service.MissaoService;
import br.com.fiap.SkyAid.util.StatusMissao;
import br.com.fiap.SkyAid.util.TipoMissao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/missoes")
public class MissaoController {

    @Autowired
    private MissaoService missaoService;

    @Autowired
    private DroneService droneService;

    // GET /api/missoes
    @GetMapping
    public ResponseEntity<List<MissaoResponseDto>> listarTodas() {
        List<MissaoResponseDto> missoes = missaoService.listarTodasMissoes()
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(missoes);
    }

    // GET /api/missoes/{id}
    @GetMapping("/{id}")
    public ResponseEntity<MissaoResponseDto> buscarPorId(@PathVariable Long id) {
        return missaoService.buscarMissaoPorId(id)
                .map(this::toResponseDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/missoes/status/{status}
    @GetMapping("/status/{status}")
    public ResponseEntity<List<MissaoResponseDto>> buscarPorStatus(@PathVariable StatusMissao status) {
        List<MissaoResponseDto> missoes = missaoService.buscarMissoesPorStatus(status)
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(missoes);
    }

    // GET /api/missoes/tipo/{tipo}
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<MissaoResponseDto>> buscarPorTipo(@PathVariable TipoMissao tipo) {
        List<MissaoResponseDto> missoes = missaoService.buscarMissoesPorTipo(tipo)
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(missoes);
    }

    // POST /api/missoes
    @PostMapping
    public ResponseEntity<MissaoResponseDto> criar(@RequestBody @Valid MissaoRequestDto dto) {
        Optional<Drone> droneOpt = droneService.buscarDronePorId(dto.getDroneId());
        if (droneOpt.isEmpty()) return ResponseEntity.badRequest().build();

        Missao missao = new Missao();
        missao.setTipo(dto.getTipo());
        missao.setStatus(dto.getStatus());
        missao.setDescricao(dto.getDescricao());
        missao.setDestinoLatitude(dto.getDestinoLatitude());
        missao.setDestinoLongitude(dto.getDestinoLongitude());
        missao.setDrone(droneOpt.get());

        Missao salva = missaoService.criarMissao(missao);
        return ResponseEntity.ok(toResponseDto(salva));
    }

    // PUT /api/missoes/{id}
    @PutMapping("/{id}")
    public ResponseEntity<MissaoResponseDto> atualizar(@PathVariable Long id, @RequestBody @Valid MissaoRequestDto dto) {
        Optional<Drone> droneOpt = droneService.buscarDronePorId(dto.getDroneId());
        if (droneOpt.isEmpty()) return ResponseEntity.badRequest().build();

        Missao missao = new Missao();
        missao.setTipo(dto.getTipo());
        missao.setStatus(dto.getStatus());
        missao.setDescricao(dto.getDescricao());
        missao.setDestinoLatitude(dto.getDestinoLatitude());
        missao.setDestinoLongitude(dto.getDestinoLongitude());
        missao.setDrone(droneOpt.get());

        Missao atualizada = missaoService.atualizarMissao(id, missao);
        return ResponseEntity.ok(toResponseDto(atualizada));
    }

    // DELETE /api/missoes/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        missaoService.deletarMissao(id);
        return ResponseEntity.noContent().build();
    }

    // Conversão entidade → DTO
    private MissaoResponseDto toResponseDto(Missao missao) {
        MissaoResponseDto dto = new MissaoResponseDto();
        dto.setId(missao.getId());
        dto.setTipo(missao.getTipo());
        dto.setStatus(missao.getStatus());
        dto.setDescricao(missao.getDescricao());
        dto.setDestinoLatitude(missao.getDestinoLatitude());
        dto.setDestinoLongitude(missao.getDestinoLongitude());
        dto.setDroneId(missao.getDrone().getId());
        dto.setNomeDrone(missao.getDrone().getIdentificador());
        return dto;
    }
}
