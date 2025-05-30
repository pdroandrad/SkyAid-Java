package br.com.fiap.SkyAid.dto.response;

import br.com.fiap.SkyAid.util.StatusMissao;
import br.com.fiap.SkyAid.util.TipoMissao;

public class MissaoResponseDto {

    private Long id;
    private TipoMissao tipo;
    private StatusMissao status;
    private String descricao;
    private Double destinoLatitude;
    private Double destinoLongitude;
    private Long droneId;
    private String nomeDrone;

    // Construtor vazio
    public MissaoResponseDto() {}

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getNomeDrone() {
        return nomeDrone;
    }

    public void setNomeDrone(String nomeDrone) {
        this.nomeDrone = nomeDrone;
    }
}
