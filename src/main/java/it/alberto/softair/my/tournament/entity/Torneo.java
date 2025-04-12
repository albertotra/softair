package it.alberto.softair.my.tournament.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "torneo")
@Getter
@Setter
public class Torneo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @Column(name = "data_inizio")
    private LocalDateTime dataInizio;

    private String luogo;

    @Column(name = "squadre_min")
    private Integer squadreMin;

    @Column(name = "squadre_max")
    private Integer squadreMax;

    @Column(name = "costo_iscrizione")
    private BigDecimal costoIscrizione;

    @Lob
    private byte[] book;

    @OneToMany(mappedBy = "torneo")
    private List<CoordinateTorneo> coordinateTornei;

}
