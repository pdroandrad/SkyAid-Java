package br.com.fiap.SkyAid.dto.request;

import br.com.fiap.SkyAid.util.StatusMissao;
import br.com.fiap.SkyAid.util.TipoMissao;
import jakarta.validation.constraints.NotNull;

public class MissaoRequestDto {

    @NotNull(message = "Tipo da missão é obrigatório")
    private TipoMissao tipo;

    @NotNull(message = "Status da missão é obrigatório")
    private StatusMissao status;

    private String descricao;

    @NotNull(message = "Destino latitude é obrigatório")
    private Double destinoLatitude;

    @NotNull(message = "Destino longitude é obrigatório")
    private Double destinoLongitude;

    @NotNull(message = "ID do drone é obrigatório")
    private Long droneId;

    // Construtor vazio
    public MissaoRequestDto() {}

    // Getters e Setters

    public TipoMissao getTipo() {
        return tipo;
    }

    public void setTipo(TipoMissao tipo) {
        this.tipo = tipo;
    }

    public StatusMissao getStatus() {
        return status;
    }

    public void setStatus(StatusMissao status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getDestinoLatitude() {
        return destinoLatitude;
    }

    public void setDestinoLatitude(Double destinoLatitude) {
        this.destinoLatitude = destinoLatitude;
    }

    public Double getDestinoLongitude() {
        return destinoLongitude;
    }

    public void setDestinoLongitude(Double destinoLongitude) {
        this.destinoLongitude = destinoLongitude;
    }

    public Long getDroneId() {
        return droneId;
    }

    public void setDroneId(Long droneId) {
        this.droneId = droneId;
    }
}
