package it.alberto.softair.my.tournament.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @ManyToOne
    @JoinColumn(name = "id_torneo")
    @JsonIgnore
    private Torneo torneo;

    @ManyToOne
    @JoinColumn(name = "id_squadra")
    @JsonBackReference
    private Squadra squadra;

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
    @JsonManagedReference
    private List<PunteggioSquadraDettaglio> dettagli;
}

