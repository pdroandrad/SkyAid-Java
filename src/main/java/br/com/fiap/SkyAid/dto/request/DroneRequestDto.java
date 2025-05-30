package br.com.fiap.SkyAid.dto.request;

import br.com.fiap.SkyAid.util.StatusDrone;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DroneRequestDto {

    @NotBlank(message = "O identificador é obrigatório")
    private String identificador;

    @NotBlank(message = "O modelo é obrigatório")
    private String modelo;

    @NotNull(message = "O status é obrigatório")
    private StatusDrone status;

    @NotNull(message = "O ID do operador é obrigatório")
    private Long operadorId;

    // Construtor vazio
    public DroneRequestDto() {}

    // Getters e Setters

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public StatusDrone getStatus() {
        return status;
    }

    public void setStatus(StatusDrone status) {
        this.status = status;
    }

    public Long getOperadorId() {
        return operadorId;
    }

    public void setOperadorId(Long operadorId) {
        this.operadorId = operadorId;
    }
}
