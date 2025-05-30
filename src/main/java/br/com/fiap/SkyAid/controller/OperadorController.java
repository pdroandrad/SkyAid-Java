package br.com.fiap.SkyAid.controller;

import br.com.fiap.SkyAid.dto.request.OperadorRequestDto;
import br.com.fiap.SkyAid.dto.response.OperadorResponseDto;
import br.com.fiap.SkyAid.model.Operador;
import br.com.fiap.SkyAid.service.OperadorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/operadores")
public class OperadorController {

    @Autowired
    private OperadorService operadorService;

    // GET /api/operadores
    @GetMapping
    public ResponseEntity<List<OperadorResponseDto>> listarTodos() {
        List<OperadorResponseDto> operadores = operadorService.listarTodosOperadores()
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(operadores);
    }

    // GET /api/operadores/{id}
    @GetMapping("/{id}")
    public ResponseEntity<OperadorResponseDto> buscarPorId(@PathVariable Long id) {
        return operadorService.buscarOperadorPorId(id)
                .map(this::toResponseDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/operadores
    @PostMapping
    public ResponseEntity<OperadorResponseDto> criar(@RequestBody @Valid OperadorRequestDto dto) {
        Operador operador = new Operador();
        operador.setNome(dto.getNome());
        operador.setEmail(dto.getEmail());
        operador.setSenha(dto.getSenha());

        Operador salvo = operadorService.criarOperador(operador);
        return ResponseEntity.ok(toResponseDto(salvo));
    }

    // POST /api/operadores/login
    @PostMapping("/login")
    public ResponseEntity<OperadorResponseDto> login(@RequestBody @Valid OperadorRequestDto dto) {
        Optional<Operador> operadorOpt = operadorService.autenticar(dto.getEmail(), dto.getSenha());

        return operadorOpt
                .map(this::toResponseDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(401).build());
    }

    // PUT /api/operadores/{id}
    @PutMapping("/{id}")
    public ResponseEntity<OperadorResponseDto> atualizar(@PathVariable Long id,
                                                         @RequestBody @Valid OperadorRequestDto dto) {
        Operador operador = new Operador();
        operador.setNome(dto.getNome());
        operador.setEmail(dto.getEmail());
        operador.setSenha(dto.getSenha());

        Operador atualizado = operadorService.atualizarOperador(id, operador);
        return ResponseEntity.ok(toResponseDto(atualizado));
    }

    // DELETE /api/operadores/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        operadorService.deletarOperador(id);
        return ResponseEntity.noContent().build();
    }

    // Conversão para DTO de resposta
    private OperadorResponseDto toResponseDto(Operador operador) {
        OperadorResponseDto dto = new OperadorResponseDto();
        dto.setId(operador.getId());
        dto.setNome(operador.getNome());
        dto.setEmail(operador.getEmail());
        return dto;
    }
}
