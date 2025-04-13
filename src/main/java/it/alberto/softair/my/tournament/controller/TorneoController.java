package it.alberto.softair.my.tournament.controller;

import it.alberto.softair.my.tournament.entity.Torneo;
import it.alberto.softair.my.tournament.service.TorneoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/torneo")
public class TorneoController {

    @Autowired
    private TorneoService torneoService;

    @GetMapping
    public ResponseEntity<List<Torneo>> getAllTornei() {
        List<Torneo> tornei = torneoService.findAll();
        return new ResponseEntity<>(tornei, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Torneo> getTorneoById(@PathVariable Integer id) {
        Optional<Torneo> torneo = torneoService.findById(id);
        return torneo.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Torneo>> getTorneiByNome(@PathVariable String nome) {
        List<Torneo> tornei = torneoService.findByNomeContaining(nome);
        return new ResponseEntity<>(tornei, HttpStatus.OK);
    }

    @GetMapping("/data-dopo/{data}")
    public ResponseEntity<List<Torneo>> getTorneiByDataInizioAfter(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime data) {
        List<Torneo> tornei = torneoService.findByDataInizioAfter(data);
        return new ResponseEntity<>(tornei, HttpStatus.OK);
    }

    @GetMapping("/luogo/{luogo}")
    public ResponseEntity<List<Torneo>> getTorneiByLuogo(@PathVariable String luogo) {
        List<Torneo> tornei = torneoService.findByLuogoContaining(luogo);
        return new ResponseEntity<>(tornei, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Torneo> createTorneo(@RequestBody Torneo torneo) {
        Torneo savedTorneo = torneoService.save(torneo);
        return new ResponseEntity<>(savedTorneo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Torneo> updateTorneo(@PathVariable Integer id, @RequestBody Torneo torneo) {
        Optional<Torneo> existingTorneo = torneoService.findById(id);
        if (existingTorneo.isPresent()) {
            torneo.setId(id);
            Torneo updatedTorneo = torneoService.save(torneo);
            return new ResponseEntity<>(updatedTorneo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTorneo(@PathVariable Integer id) {
        Optional<Torneo> torneo = torneoService.findById(id);
        if (torneo.isPresent()) {
            torneoService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Legacy endpoint for compatibility
    @GetMapping("/")
    public String hello() {
        return "Hello, world!";
    }
}