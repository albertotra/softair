package it.alberto.softair.my.tournament.controller;

import it.alberto.softair.my.tournament.entity.CoordinateTorneo;
import it.alberto.softair.my.tournament.service.CoordinateTorneoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/coordinate-torneo")
public class CoordinateTorneoController {

    @Autowired
    private CoordinateTorneoService coordinateTorneoService;

    @GetMapping
    public ResponseEntity<List<CoordinateTorneo>> getAllCoordinateTorneo() {
        List<CoordinateTorneo> coordinateTornei = coordinateTorneoService.findAll();
        return new ResponseEntity<>(coordinateTornei, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoordinateTorneo> getCoordinateTorneoById(@PathVariable Integer id) {
        Optional<CoordinateTorneo> coordinateTorneo = coordinateTorneoService.findById(id);
        return coordinateTorneo.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/torneo/{torneoId}")
    public ResponseEntity<List<CoordinateTorneo>> getCoordinateTorneoByTorneoId(@PathVariable Integer torneoId) {
        List<CoordinateTorneo> coordinateTornei = coordinateTorneoService.findByTorneoId(torneoId);
        return new ResponseEntity<>(coordinateTornei, HttpStatus.OK);
    }

    @GetMapping("/coordinate/{coordinateId}")
    public ResponseEntity<List<CoordinateTorneo>> getCoordinateTorneoByCoordinateId(@PathVariable Integer coordinateId) {
        List<CoordinateTorneo> coordinateTornei = coordinateTorneoService.findByCoordinateId(coordinateId);
        return new ResponseEntity<>(coordinateTornei, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CoordinateTorneo> createCoordinateTorneo(@RequestBody CoordinateTorneo coordinateTorneo) {
        CoordinateTorneo savedCoordinateTorneo = coordinateTorneoService.save(coordinateTorneo);
        return new ResponseEntity<>(savedCoordinateTorneo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CoordinateTorneo> updateCoordinateTorneo(@PathVariable Integer id, @RequestBody CoordinateTorneo coordinateTorneo) {
        Optional<CoordinateTorneo> existingCoordinateTorneo = coordinateTorneoService.findById(id);
        if (existingCoordinateTorneo.isPresent()) {
            coordinateTorneo.setId(id);
            CoordinateTorneo updatedCoordinateTorneo = coordinateTorneoService.save(coordinateTorneo);
            return new ResponseEntity<>(updatedCoordinateTorneo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoordinateTorneo(@PathVariable Integer id) {
        Optional<CoordinateTorneo> coordinateTorneo = coordinateTorneoService.findById(id);
        if (coordinateTorneo.isPresent()) {
            coordinateTorneoService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}