package br.com.fiap.SkyAid.service;

import br.com.fiap.SkyAid.exception.ResourceNotFoundException;
import br.com.fiap.SkyAid.model.Operador;
import br.com.fiap.SkyAid.repository.OperadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperadorService {

    private final OperadorRepository operadorRepository;

    @Autowired
    public OperadorService(OperadorRepository operadorRepository) {
        this.operadorRepository = operadorRepository;
    }

    // CREATE
    public Operador criarOperador(Operador operador) {
        if (operadorRepository.existsByEmail(operador.getEmail())) {
            throw new RuntimeException("Já existe um operador com este e-mail: " + operador.getEmail());
        }
        return operadorRepository.save(operador);
    }

    // READ - Todos
    public List<Operador> listarTodosOperadores() {
        return operadorRepository.findAll();
    }

    // READ - Por ID
    public Optional<Operador> buscarOperadorPorId(Long id) {
        return operadorRepository.findById(id);
    }

    // Autenticação simples (exemplo)
    public Optional<Operador> autenticar(String email, String senha) {
        Operador operador = operadorRepository.findByEmailAndSenha(email, senha);
        return Optional.ofNullable(operador);
    }

    // UPDATE
    public Operador atualizarOperador(Long id, Operador operadorAtualizado) {
        return operadorRepository.findById(id)
                .map(operador -> {
                    operador.setNome(operadorAtualizado.getNome());
                    operador.setEmail(operadorAtualizado.getEmail());
                    operador.setSenha(operadorAtualizado.getSenha());
                    return operadorRepository.save(operador);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Operador", "id", id));
    }

    // DELETE
    public void deletarOperador(Long id) {
        if (!operadorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Operador", "id", id);
        }
        operadorRepository.deleteById(id);
    }
}
