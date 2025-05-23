package it.alberto.softair.my.tournament.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PunteggioSquadraDettaglio")
@Getter
@Setter
public class PunteggioSquadraDettaglio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_punteggio_squadra")
    @JsonBackReference
    private PunteggioSquadra punteggioSquadra;

    @Column(name = "chiave")
    private String chiave;

    @Column(name = "valore")
    private String valore;
}
