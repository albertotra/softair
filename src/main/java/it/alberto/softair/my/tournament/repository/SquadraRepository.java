package it.alberto.softair.my.tournament.repository;

import it.alberto.softair.my.tournament.entity.Squadra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SquadraRepository extends JpaRepository<Squadra, Integer> {
    List<Squadra> findByNomeContaining(String nome);
    List<Squadra> findByComitato(String comitato);
}