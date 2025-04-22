package it.alberto.softair.my.tournament.repository;

import it.alberto.softair.my.tournament.entity.PunteggioSquadra;
import it.alberto.softair.my.tournament.entity.PunteggioSquadraDettaglio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PunteggioSquadraDettaglioRepository extends JpaRepository<PunteggioSquadraDettaglio, Long> {
}
