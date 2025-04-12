package it.alberto.softair.my.tournament.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Table(name = "obiettivo")
@Getter
@Setter
public class Obiettivo {

    @Id
    private Integer id;

    private String nome;
    private String descrizione;
    private String tipologia;

    @Column(name = "fase_e")
    private Boolean faseE;

}
