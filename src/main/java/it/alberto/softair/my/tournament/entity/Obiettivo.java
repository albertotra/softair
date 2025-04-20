package it.alberto.softair.my.tournament.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "obiettivo")
@Getter
@Setter
public class Obiettivo {

    @Id
    private Integer id;

    @Column(name = "id_torneo")
    private Integer idTorneo;

    private String nome;
    private String descrizione;
    private String tipologia;

    @Column(name = "fase_e")
    private Boolean faseE;

    private Integer durata;

    @Column(name = "canale_comunicazioni")
    private String canaleComunicazioni;

    @Column(name = "materiale_obbligatorio")
    private String materialeObbligatorio;

    @OneToMany(mappedBy = "obiettivo")
    @JsonManagedReference
    private List<Punteggio> punteggi;
}
