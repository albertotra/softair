package it.alberto.softair.my.tournament.controller;

import it.alberto.softair.my.tournament.dto.DettaglioTorneoSquadraDto;
import it.alberto.softair.my.tournament.dto.TorneoSquadraDto;
import it.alberto.softair.my.tournament.entity.Torneo;
import it.alberto.softair.my.tournament.service.TorneoSquadraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/torneo-squadra")
public class TorneoSquadraController {

    @Autowired
    private TorneoSquadraService torneoSquadraService;

    @GetMapping("/{idTorneo}")
    public ResponseEntity<List<DettaglioTorneoSquadraDto>> getTorneoById(@PathVariable Integer idTorneo) {
        List<DettaglioTorneoSquadraDto> torneoSquadraList = torneoSquadraService.getByTorneoId(idTorneo);
        return new ResponseEntity<>(torneoSquadraList, HttpStatus.OK);
    }
}
