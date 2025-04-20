package it.alberto.softair.my.tournament.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "torneo_squadra")
@Getter
@Setter
public class TorneoSquadra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_torneo")
    private Integer idTorneo;

    @Column(name = "id_squadra")
    private Integer idSquadra;

    @Column(name = "callsign", length = 45)
    private String callSign;

    @Column(name = "radio_ch", length = 45)
    private String radioCh;

    @Column(name = "ora_test_asg")
    private LocalDateTime oraTestAsg;

    @Column(name = "ora_incursione")
    private LocalDateTime oraIncursione;
}
