package it.alberto.softair.my.tournament.controller;

import it.alberto.softair.my.tournament.entity.Classifica;
import it.alberto.softair.my.tournament.entity.Obiettivo;
import it.alberto.softair.my.tournament.service.ClassificaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classifica")
public class ClassificaController {

    @Autowired
    private ClassificaService classificaService;

    @PostMapping("/calcola/{idTorneo}")
    public ResponseEntity<List<Classifica>> calcolaByIdTorneo(@PathVariable Integer idTorneo) {
        List<Classifica> classificaList= classificaService.calcolaClassifica(idTorneo);
        return new ResponseEntity<>(classificaList, HttpStatus.OK);
    }

    @GetMapping("/{idTorneo}")
    public ResponseEntity<List<Classifica>> findByIdTorneo(@PathVariable Integer idTorneo) {
        List<Classifica> classificaList= classificaService.getAllByIdTorneo(idTorneo);
        return new ResponseEntity<>(classificaList, HttpStatus.OK);
    }

}
