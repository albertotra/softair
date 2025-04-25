package it.alberto.softair.my.tournament.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PunteggioEInputDto {
    private int id;
    private String descrizione;
    private int valore;
    private boolean selezionato;
}
