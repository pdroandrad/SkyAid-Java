package br.com.fiap.SkyAid.dto.response;

import br.com.fiap.SkyAid.util.TipoSensor;

import java.time.LocalDateTime;

public class RegistroSensorResponseDto {

    private Long id;
    private TipoSensor tipo;
    private Double valor;
    private LocalDateTime dataHora;
    private Double latitude;
    private Double longitude;
    private Long droneId;
    private String nomeDrone;

    // Construtor vazio
    public RegistroSensorResponseDto() {}

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getNomeDrone() {
        return nomeDrone;
    }

    public void setNomeDrone(String nomeDrone) {
        this.nomeDrone = nomeDrone;
    }
}
