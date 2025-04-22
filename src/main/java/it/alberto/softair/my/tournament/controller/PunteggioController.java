package it.alberto.softair.my.tournament.controller;

import it.alberto.softair.my.tournament.dto.DettaglioTorneoSquadraDto;
import it.alberto.softair.my.tournament.entity.Obiettivo;
import it.alberto.softair.my.tournament.entity.Punteggio;
import it.alberto.softair.my.tournament.entity.PunteggioE;
import it.alberto.softair.my.tournament.entity.PunteggioSquadra;
import it.alberto.softair.my.tournament.service.PunteggioService;
import it.alberto.softair.my.tournament.service.TorneoSquadraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/punteggio")
public class PunteggioController {

    @Autowired
    private PunteggioService punteggioService;

    @GetMapping("/{idPunteggio}")
    public ResponseEntity<Punteggio> getPunteggioById(@PathVariable Integer idPunteggio) {
        Punteggio punteggio = punteggioService.getById(idPunteggio);
        return new ResponseEntity<>(punteggio, HttpStatus.OK);
    }
}
