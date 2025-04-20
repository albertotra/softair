package it.alberto.softair.my.tournament.repository;

import it.alberto.softair.my.tournament.entity.Obiettivo;
import it.alberto.softair.my.tournament.entity.Punteggio;
import it.alberto.softair.my.tournament.entity.PunteggioE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PunteggioERepository extends JpaRepository<PunteggioE, Integer> {
}