package it.alberto.softair.my.tournament.repository;

import it.alberto.softair.my.tournament.entity.TorneoSquadra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TorneoSquadraRepository extends JpaRepository<TorneoSquadra, Long> {
    List<TorneoSquadra> findByIdTorneo(Integer idTorneo);
}
