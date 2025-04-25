package it.alberto.softair.my.tournament.repository;

import it.alberto.softair.my.tournament.entity.Classifica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassificaRepository extends JpaRepository<Classifica, Integer> {
    List<Classifica> findByTorneo_idOrderByPunteggioDesc(Integer idTorneo);
    Classifica findBySquadra_id(Integer idSquadraa);

}