package br.com.fiap.SkyAid.repository;

import br.com.fiap.SkyAid.model.RegistroSensor;
import br.com.fiap.SkyAid.util.TipoSensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroSensorRepository extends JpaRepository<RegistroSensor, Long> {
    List<RegistroSensor> findByTipo(TipoSensor tipo);
}
