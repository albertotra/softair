package it.alberto.softair.my.tournament.dto;

import lombok.Data;

@Data
public class CoordinateDto {
    private Integer id;
    private String latitudine;
    private String longitudine;
    private String nome;
    private String descrizione;
}
