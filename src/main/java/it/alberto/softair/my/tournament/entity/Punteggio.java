package it.alberto.softair.my.tournament.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Entity
@Table(name = "punteggio")
@Getter
@Setter
public class Punteggio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_torneo")
    @JsonIgnore
    private Torneo torneo;

    @ManyToOne
    @JoinColumn(name = "id_obiettivo")
    private Obiettivo obiettivo;

    @Column(name = "ribelli")
    private Boolean ribelli;

    @Column(name = "n_ribelli")
    private Integer numeroRibelli;

    @Column(name = "difensori")
    private Boolean difensori;

    @Column(name = "n_difensori")
    private Integer numeroDifensori;

    @Column(name = "civili")
    private Boolean civili;

    @Column(name = "n_civili")
    private Integer numeroCivili;

    @Column(name = "vip")
    private Boolean vip;

    @Column(name = "n_vip")
    private Integer numeroVip;

    @OneToMany(mappedBy = "punteggio")
    @JsonManagedReference
    private List<PunteggioE> punteggiE;
}
