package it.alberto.softair.my.tournament.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/torneo")
public class TorneoController {

    @GetMapping("/")
    public String hello() {
        return "Hello, world!";
    }
}
