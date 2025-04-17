package it.alberto.softair.my.tournament.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class TorneoDto {

    private Integer id;
    private String nome;
    private LocalDateTime dataInizio;
    private String luogo;
    private Integer squadreMin;
    private Integer squadreMax;
    private BigDecimal costoIscrizione;
    private String squadraOrganizzatrice;
    private String parcheggio;
    private String fob;
}
