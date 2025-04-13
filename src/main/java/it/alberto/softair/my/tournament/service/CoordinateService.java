package it.alberto.softair.my.tournament.service;

import it.alberto.softair.my.tournament.entity.Coordinate;
import it.alberto.softair.my.tournament.repository.CoordinateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoordinateService {

    @Autowired
    private CoordinateRepository coordinateRepository;

    public List<Coordinate> findAll() {
        return coordinateRepository.findAll();
    }

    public Optional<Coordinate> findById(Integer id) {
        return coordinateRepository.findById(id);
    }

    public Coordinate save(Coordinate coordinate) {
        return coordinateRepository.save(coordinate);
    }

    public void deleteById(Integer id) {
        coordinateRepository.deleteById(id);
    }
}