package it.alberto.softair.my.tournament.service;

import it.alberto.softair.my.tournament.entity.CoordinateTorneo;
import it.alberto.softair.my.tournament.repository.CoordinateTorneoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoordinateTorneoService {

    @Autowired
    private CoordinateTorneoRepository coordinateTorneoRepository;

    public List<CoordinateTorneo> findAll() {
        return coordinateTorneoRepository.findAll();
    }

    public Optional<CoordinateTorneo> findById(Integer id) {
        return coordinateTorneoRepository.findById(id);
    }

    public List<CoordinateTorneo> findByTorneoId(Integer torneoId) {
        return coordinateTorneoRepository.findByTorneoId(torneoId);
    }

    public List<CoordinateTorneo> findByCoordinateId(Integer coordinateId) {
        return coordinateTorneoRepository.findByCoordinateId(coordinateId);
    }

    public CoordinateTorneo save(CoordinateTorneo coordinateTorneo) {
        return coordinateTorneoRepository.save(coordinateTorneo);
    }

    public void deleteById(Integer id) {
        coordinateTorneoRepository.deleteById(id);
    }
}