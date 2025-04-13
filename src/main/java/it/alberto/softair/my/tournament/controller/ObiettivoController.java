package it.alberto.softair.my.tournament.controller;

import it.alberto.softair.my.tournament.entity.Obiettivo;
import it.alberto.softair.my.tournament.service.ObiettivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/obiettivo")
public class ObiettivoController {

    @Autowired
    private ObiettivoService obiettivoService;

    @GetMapping
    public ResponseEntity<List<Obiettivo>> getAllObiettivi() {
        List<Obiettivo> obiettivi = obiettivoService.findAll();
        return new ResponseEntity<>(obiettivi, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Obiettivo> getObiettivoById(@PathVariable Integer id) {
        Optional<Obiettivo> obiettivo = obiettivoService.findById(id);
        return obiettivo.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/fase-e/{faseE}")
    public ResponseEntity<List<Obiettivo>> getObiettiviByFaseE(@PathVariable Boolean faseE) {
        List<Obiettivo> obiettivi = obiettivoService.findByFaseE(faseE);
        return new ResponseEntity<>(obiettivi, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Obiettivo> createObiettivo(@RequestBody Obiettivo obiettivo) {
        Obiettivo savedObiettivo = obiettivoService.save(obiettivo);
        return new ResponseEntity<>(savedObiettivo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Obiettivo> updateObiettivo(@PathVariable Integer id, @RequestBody Obiettivo obiettivo) {
        Optional<Obiettivo> existingObiettivo = obiettivoService.findById(id);
        if (existingObiettivo.isPresent()) {
            obiettivo.setId(id);
            Obiettivo updatedObiettivo = obiettivoService.save(obiettivo);
            return new ResponseEntity<>(updatedObiettivo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteObiettivo(@PathVariable Integer id) {
        Optional<Obiettivo> obiettivo = obiettivoService.findById(id);
        if (obiettivo.isPresent()) {
            obiettivoService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}