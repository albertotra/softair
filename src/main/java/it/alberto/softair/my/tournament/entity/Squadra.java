package it.alberto.softair.my.tournament.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

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

    @Column(name = "num_operatori")
    private Integer numOperatori;

    private String comitato;

}
