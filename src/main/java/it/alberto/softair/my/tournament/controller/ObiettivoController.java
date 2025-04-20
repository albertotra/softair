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

    @GetMapping("/{idTorneo}")
    public ResponseEntity<List<Obiettivo>> findByIdTorneo(@PathVariable Integer idTorneo) {
        List<Obiettivo> obiettivi = obiettivoService.findByIdTorneo(idTorneo);
        return new ResponseEntity<>(obiettivi, HttpStatus.OK);
    }

}