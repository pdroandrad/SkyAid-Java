package br.com.fiap.SkyAid.repository;

import br.com.fiap.SkyAid.model.Operador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperadorRepository extends JpaRepository<Operador, Long> {
    boolean existsByEmail(String email);
    Operador findByEmailAndSenha(String email, String senha);
}
