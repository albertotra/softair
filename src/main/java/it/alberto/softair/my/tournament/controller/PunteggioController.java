package it.alberto.softair.my.tournament.controller;

import it.alberto.softair.my.tournament.dto.DettaglioTorneoSquadraDto;
import it.alberto.softair.my.tournament.entity.Obiettivo;
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

    @GetMapping("/{idTorneo}/{idObiettivo}")
    public ResponseEntity<List<Obiettivo>> getPunteggioByIdTorneoAndIdObiettivo(@PathVariable Integer idTorneo,
                                                                         @PathVariable Integer idObiettivo) {
        List<Obiettivo> torneoSquadraList = punteggioService.getByTorneoId(idTorneo);
        return new ResponseEntity<>(torneoSquadraList, HttpStatus.OK);
    }
}
