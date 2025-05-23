package it.alberto.softair.my.tournament.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "squadra")
@Getter
@Setter
public class Squadra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String comitato;

    @OneToMany(mappedBy = "squadra")
    @JsonManagedReference
    private List<PunteggioSquadra> punteggioSquadra;
}
