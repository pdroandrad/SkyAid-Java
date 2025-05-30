package br.com.fiap.SkyAid.service;

import br.com.fiap.SkyAid.exception.ResourceNotFoundException;
import br.com.fiap.SkyAid.model.Drone;
import br.com.fiap.SkyAid.repository.DroneRepository;
import br.com.fiap.SkyAid.util.StatusDrone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DroneService {

    private final DroneRepository droneRepository;

    @Autowired
    public DroneService(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    // CREATE
    public Drone criarDrone(Drone drone) {
        return droneRepository.save(drone);
    }

    // READ - Todos
    public List<Drone> listarTodosDrones() {
        return droneRepository.findAll();
    }

    // READ - Por ID
    public Optional<Drone> buscarDronePorId(Long id) {
        return droneRepository.findById(id);
    }

    // READ - Por Status
    public List<Drone> buscarDronesPorStatus(StatusDrone status) {
        return droneRepository.findByStatus(status);
    }

    // UPDATE
    public Drone atualizarDrone(Long id, Drone droneAtualizado) {
        return droneRepository.findById(id)
                .map(drone -> {
                    drone.setIdentificador(droneAtualizado.getIdentificador());
                    drone.setModelo(droneAtualizado.getModelo());
                    drone.setStatus(droneAtualizado.getStatus());
                    drone.setOperador(droneAtualizado.getOperador());
                    return droneRepository.save(drone);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Drone", "id", id));
    }

    // DELETE
    public void deletarDrone(Long id) {
        if (!droneRepository.existsById(id)) {
            throw new ResourceNotFoundException("Drone", "id", id);
        }
        droneRepository.deleteById(id);
    }
}
