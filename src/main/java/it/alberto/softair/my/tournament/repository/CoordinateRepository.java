package it.alberto.softair.my.tournament.repository;

import it.alberto.softair.my.tournament.entity.Coordinate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordinateRepository extends JpaRepository<Coordinate, Integer> {
}