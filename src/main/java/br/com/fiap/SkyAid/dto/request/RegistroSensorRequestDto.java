package br.com.fiap.SkyAid.dto.request;

import br.com.fiap.SkyAid.util.TipoSensor;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class RegistroSensorRequestDto {

    @NotNull(message = "Tipo do sensor é obrigatório")
    private TipoSensor tipo;

    @NotNull(message = "Valor é obrigatório")
    private Double valor;

    @NotNull(message = "Data e hora são obrigatórias")
    private LocalDateTime dataHora;

    private Double latitude;

    private Double longitude;

    @NotNull(message = "ID do drone é obrigatório")
    private Long droneId;

    // Construtor vazio
    public RegistroSensorRequestDto() {}

    // Getters e Setters

    public TipoSensor getTipo() {
        return tipo;
    }

    public void setTipo(TipoSensor tipo) {
        this.tipo = tipo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Long getDroneId() {
        return droneId;
    }

    public void setDroneId(Long droneId) {
        this.droneId = droneId;
    }
}
