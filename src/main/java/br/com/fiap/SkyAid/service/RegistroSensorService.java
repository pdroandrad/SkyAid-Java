package br.com.fiap.SkyAid.service;

import br.com.fiap.SkyAid.exception.ResourceNotFoundException;
import br.com.fiap.SkyAid.model.RegistroSensor;
import br.com.fiap.SkyAid.repository.RegistroSensorRepository;
import br.com.fiap.SkyAid.util.TipoSensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistroSensorService {

    private final RegistroSensorRepository registroSensorRepository;

    @Autowired
    public RegistroSensorService(RegistroSensorRepository registroSensorRepository) {
        this.registroSensorRepository = registroSensorRepository;
    }

    // CREATE
    public RegistroSensor criarRegistroSensor(RegistroSensor registroSensor) {
        return registroSensorRepository.save(registroSensor);
    }

    // READ - Todos
    public List<RegistroSensor> listarTodosRegistros() {
        return registroSensorRepository.findAll();
    }

    // READ - Por ID
    public Optional<RegistroSensor> buscarRegistroPorId(Long id) {
        return registroSensorRepository.findById(id);
    }

    // READ - Por Tipo
    public List<RegistroSensor> buscarRegistrosPorTipo(TipoSensor tipo) {
        return registroSensorRepository.findByTipo(tipo);
    }

    // UPDATE
    public RegistroSensor atualizarRegistroSensor(Long id, RegistroSensor registroAtualizado) {
        return registroSensorRepository.findById(id)
                .map(registro -> {
                    registro.setTipo(registroAtualizado.getTipo());
                    registro.setValor(registroAtualizado.getValor());
                    registro.setDataHora(registroAtualizado.getDataHora());
                    registro.setDrone(registroAtualizado.getDrone());
                    return registroSensorRepository.save(registro);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Registro de sensor", "id", id));
    }


    // DELETE
    public void deletarRegistroSensor(Long id) {
        if (!registroSensorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Registro de sensor", "id", id);
        }
        registroSensorRepository.deleteById(id);
    }
}
