package it.alberto.softair.my.tournament.controller;

import it.alberto.softair.my.tournament.repository.TorneoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/torneo")
public class TorneoController {

    @Autowired
    private TorneoRepository torneoRepository;

    @GetMapping("/")
    public String hello() {
        return "Hello, world!";
    }
}
