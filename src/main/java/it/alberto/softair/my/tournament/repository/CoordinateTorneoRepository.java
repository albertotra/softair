package it.alberto.softair.my.tournament.repository;

import it.alberto.softair.my.tournament.entity.CoordinateTorneo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoordinateTorneoRepository extends JpaRepository<CoordinateTorneo, Integer> {
    List<CoordinateTorneo> findByTorneoId(Integer torneoId);
    List<CoordinateTorneo> findByCoordinateId(Integer coordinateId);
}