package br.com.fiap.SkyAid.repository;

import br.com.fiap.SkyAid.model.Missao;
import br.com.fiap.SkyAid.util.StatusMissao;
import br.com.fiap.SkyAid.util.TipoMissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissaoRepository extends JpaRepository<Missao, Long> {
    List<Missao> findByStatus(StatusMissao status);
    List<Missao> findByTipo(TipoMissao tipo);
}
