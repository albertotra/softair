package it.alberto.softair.my.tournament.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TorneoSquadraDto {
    private Integer id;
    private Integer idTorneo;
    private Integer idSquadra;
    private String callSign;
    private String radioCh;
    private LocalDateTime oraTestAsg;
    private LocalDateTime oraIncursione;
}
