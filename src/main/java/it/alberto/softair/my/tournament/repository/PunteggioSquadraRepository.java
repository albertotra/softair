package it.alberto.softair.my.tournament.repository;

import it.alberto.softair.my.tournament.entity.PunteggioSquadra;
import it.alberto.softair.my.tournament.entity.TorneoSquadra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PunteggioSquadraRepository extends JpaRepository<PunteggioSquadra, Long> {
    PunteggioSquadra findBySquadra_idAndTorneo_idAndPunteggio_id(Integer idSquadra, Integer idTorneo, Integer idPunteggio);
    List<PunteggioSquadra> findByTorneo_id(Integer idTorneo);
    Integer countBySquadra_idAndTorneo_id(Integer idSquadra, Integer idTorneo);
    List<PunteggioSquadra> findBySquadra_idAndTorneo_id(Integer idSquadra, Integer idTorneo);
}
