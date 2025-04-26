package it.alberto.softair.my.tournament.controller;

import it.alberto.softair.my.tournament.dto.PunteggioInputDto;
import it.alberto.softair.my.tournament.dto.SalvaPunteggioResponseDto;
import it.alberto.softair.my.tournament.entity.Punteggio;
import it.alberto.softair.my.tournament.entity.PunteggioSquadra;
import it.alberto.softair.my.tournament.service.PunteggioService;
import it.alberto.softair.my.tournament.service.PunteggioSquadraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/punteggio-squadra")
public class PunteggioSquadraController {

    @Autowired
    private PunteggioSquadraService punteggioSquadraService;

    @GetMapping("/{idSquadra}/{idTorneo}")
    public ResponseEntity<List<PunteggioSquadra>> getPunteggioSquadraByIdSquadraAndTorneoId(@PathVariable Integer idSquadra, @PathVariable Integer idTorneo) {
        List<PunteggioSquadra> punteggi = punteggioSquadraService.getPunteggioSquadraBySquadraIdAndTorneoId(idSquadra, idTorneo);
        return new ResponseEntity<>(punteggi, HttpStatus.OK);
    }

}
