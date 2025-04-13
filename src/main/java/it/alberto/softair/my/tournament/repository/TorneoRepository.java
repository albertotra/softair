package it.alberto.softair.my.tournament.repository;

import it.alberto.softair.my.tournament.entity.Torneo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TorneoRepository extends JpaRepository<Torneo, Integer> {
    List<Torneo> findByNomeContaining(String nome);
    List<Torneo> findByDataInizioAfter(LocalDateTime data);
    List<Torneo> findByLuogoContaining(String luogo);
}