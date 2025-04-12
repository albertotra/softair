package it.alberto.softair.my.tournament.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "coordinate")
@Getter
@Setter
public class Coordinate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String latitudine;
    private String longitudine;
    private String nome;
    private String descrizione;

    @OneToMany(mappedBy = "coordinate")
    private List<CoordinateTorneo> coordinateTornei;
}
