package it.alberto.softair.my.tournament.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "punteggio_e")
@Getter
@Setter
public class PunteggioE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_punteggio")
    @JsonBackReference
    private Punteggio punteggio;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "valore")
    private Integer valore;
}

