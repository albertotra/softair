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

    @GetMapping("/torneo/{idTorneo}/{idSquadra}")
    public ResponseEntity<List<Obiettivo>> findByIdTorneo(@PathVariable Integer idTorneo, @PathVariable Integer idSquadra) {
        List<Obiettivo> obiettivi = obiettivoService.findByIdTorneo(idTorneo, idSquadra);
        return new ResponseEntity<>(obiettivi, HttpStatus.OK);
    }

    @GetMapping("/{idObiettivo}")
    public ResponseEntity<Obiettivo> findByIdObiettivo(@PathVariable Integer idObiettivo) {
        Obiettivo obiettivo = obiettivoService.findByIdObiettivo(idObiettivo);
        return new ResponseEntity<>(obiettivo, HttpStatus.OK);
    }

}