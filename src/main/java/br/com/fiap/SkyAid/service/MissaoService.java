package br.com.fiap.SkyAid.service;

import br.com.fiap.SkyAid.exception.ResourceNotFoundException;
import br.com.fiap.SkyAid.model.Missao;
import br.com.fiap.SkyAid.repository.MissaoRepository;
import br.com.fiap.SkyAid.util.StatusMissao;
import br.com.fiap.SkyAid.util.TipoMissao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MissaoService {

    private final MissaoRepository missaoRepository;

    @Autowired
    public MissaoService(MissaoRepository missaoRepository) {
        this.missaoRepository = missaoRepository;
    }

    // CREATE
    public Missao criarMissao(Missao missao) {
        return missaoRepository.save(missao);
    }

    // READ - Todos
    public List<Missao> listarTodasMissoes() {
        return missaoRepository.findAll();
    }

    // READ - Por ID
    public Optional<Missao> buscarMissaoPorId(Long id) {
        return missaoRepository.findById(id);
    }

    // READ - Por Status
    public List<Missao> buscarMissoesPorStatus(StatusMissao status) {
        return missaoRepository.findByStatus(status);
    }

    // READ - Por Tipo
    public List<Missao> buscarMissoesPorTipo(TipoMissao tipo) {
        return missaoRepository.findByTipo(tipo);
    }

    // UPDATE
    public Missao atualizarMissao(Long id, Missao missaoAtualizada) {
        return missaoRepository.findById(id)
                .map(missao -> {
                    missao.setTipo(missaoAtualizada.getTipo());
                    missao.setStatus(missaoAtualizada.getStatus());
                    missao.setDescricao(missaoAtualizada.getDescricao());
                    missao.setDestinoLatitude(missaoAtualizada.getDestinoLatitude());
                    missao.setDestinoLongitude(missaoAtualizada.getDestinoLongitude());
                    missao.setDrone(missaoAtualizada.getDrone());
                    return missaoRepository.save(missao);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Missão", "id", id));
    }

    // DELETE
    public void deletarMissao(Long id) {
        if (!missaoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Missão", "id", id);
        }
        missaoRepository.deleteById(id);
    }
}
