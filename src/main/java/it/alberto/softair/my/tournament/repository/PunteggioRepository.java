package it.alberto.softair.my.tournament.repository;

import it.alberto.softair.my.tournament.entity.Obiettivo;
import it.alberto.softair.my.tournament.entity.Punteggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PunteggioRepository extends JpaRepository<Punteggio, Integer> {
    List<Obiettivo> findByTorneo_id(Integer idTorneo);
}