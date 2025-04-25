package it.alberto.softair.my.tournament.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PunteggioInputDto {
    private LocalDateTime inizioObj;
    private LocalDateTime fineObj;
    private Integer minutiImpiegati;
    private Integer numeroRibelli;
    private Integer numeroDifensori;
    private Integer numeroCivili;
    private String contestazione;
    private String note;
    private boolean fuoriFinestra;
    private List<PunteggioEInputDto> punteggiE;
    private List<PenalitaDto> penalita;
    private Integer idPunteggio;
    private Integer idObiettivo;
    private Integer idSquadra;

}
