package br.com.fiap.SkyAid.repository;

import br.com.fiap.SkyAid.model.Drone;
import br.com.fiap.SkyAid.util.StatusDrone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {
    List<Drone> findByStatus(StatusDrone status);
}
