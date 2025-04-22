package it.alberto.softair.my.tournament.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "punteggio_squadra")
@Getter
@Setter
public class PunteggioSquadra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Relazione con Torneo
    @ManyToOne
    @JoinColumn(name = "id_torneo")
    private Torneo torneo;

    // Relazione con Squadra
    @ManyToOne
    @JoinColumn(name = "id_squadra")
    private Squadra squadra;

    // Relazione con Punteggio
    @ManyToOne
    @JoinColumn(name = "id_punteggio")
    private Punteggio punteggio;

    private Integer totale;

    @Lob
    @Column(columnDefinition = "MEDIUMTEXT")
    private String contestazione;

    @Lob
    @Column(columnDefinition = "MEDIUMTEXT")
    private String note;

    @Column(name = "inizio_obj")
    private LocalDateTime inizioObj;

    @Column(name = "fine_obj")
    private LocalDateTime fineObj;

    @Column(name = "min_impiegati")
    private Integer minImpiegati;

    @OneToMany(mappedBy = "punteggioSquadra")
    private List<PunteggioSquadraDettaglio> dettagli;
}

