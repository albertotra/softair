package it.alberto.softair.my.tournament.controller;

import it.alberto.softair.my.tournament.entity.Coordinate;
import it.alberto.softair.my.tournament.service.CoordinateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/coordinate")
public class CoordinateController {

    @Autowired
    private CoordinateService coordinateService;

    @GetMapping
    public ResponseEntity<List<Coordinate>> getAllCoordinate() {
        List<Coordinate> coordinate = coordinateService.findAll();
        return new ResponseEntity<>(coordinate, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coordinate> getCoordinateById(@PathVariable Integer id) {
        Optional<Coordinate> coordinate = coordinateService.findById(id);
        return coordinate.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Coordinate> createCoordinate(@RequestBody Coordinate coordinate) {
        Coordinate savedCoordinate = coordinateService.save(coordinate);
        return new ResponseEntity<>(savedCoordinate, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coordinate> updateCoordinate(@PathVariable Integer id, @RequestBody Coordinate coordinate) {
        Optional<Coordinate> existingCoordinate = coordinateService.findById(id);
        if (existingCoordinate.isPresent()) {
            coordinate.setId(id);
            Coordinate updatedCoordinate = coordinateService.save(coordinate);
            return new ResponseEntity<>(updatedCoordinate, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoordinate(@PathVariable Integer id) {
        Optional<Coordinate> coordinate = coordinateService.findById(id);
        if (coordinate.isPresent()) {
            coordinateService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}