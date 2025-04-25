package it.alberto.softair.my.tournament.repository;

import it.alberto.softair.my.tournament.entity.Obiettivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObiettivoRepository extends JpaRepository<Obiettivo, Integer> {
    List<Obiettivo> findByFaseE(Boolean faseE);

    List<Obiettivo> findByIdTorneo(Integer idTorneo);

    Integer countByIdTorneo(Integer idTorneo);
}