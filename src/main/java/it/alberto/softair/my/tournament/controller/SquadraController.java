package it.alberto.softair.my.tournament.controller;

import it.alberto.softair.my.tournament.entity.Squadra;
import it.alberto.softair.my.tournament.service.SquadraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/squadra")
public class SquadraController {

    @Autowired
    private SquadraService squadraService;

    @GetMapping
    public ResponseEntity<List<Squadra>> getAllSquadre() {
        List<Squadra> squadre = squadraService.findAll();
        return new ResponseEntity<>(squadre, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Squadra> getSquadraById(@PathVariable Integer id) {
        Optional<Squadra> squadra = squadraService.findById(id);
        return squadra.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Squadra>> getSquadreByNome(@PathVariable String nome) {
        List<Squadra> squadre = squadraService.findByNomeContaining(nome);
        return new ResponseEntity<>(squadre, HttpStatus.OK);
    }

    @GetMapping("/comitato/{comitato}")
    public ResponseEntity<List<Squadra>> getSquadreByComitato(@PathVariable String comitato) {
        List<Squadra> squadre = squadraService.findByComitato(comitato);
        return new ResponseEntity<>(squadre, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Squadra> createSquadra(@RequestBody Squadra squadra) {
        Squadra savedSquadra = squadraService.save(squadra);
        return new ResponseEntity<>(savedSquadra, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Squadra> updateSquadra(@PathVariable Integer id, @RequestBody Squadra squadra) {
        Optional<Squadra> existingSquadra = squadraService.findById(id);
        if (existingSquadra.isPresent()) {
            squadra.setId(id);
            Squadra updatedSquadra = squadraService.save(squadra);
            return new ResponseEntity<>(updatedSquadra, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSquadra(@PathVariable Integer id) {
        Optional<Squadra> squadra = squadraService.findById(id);
        if (squadra.isPresent()) {
            squadraService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}