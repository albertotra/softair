package it.alberto.softair.my.tournament.enumeration;

import java.util.Arrays;
import java.util.Optional;

public enum InfrazioneEnum {
    FASCIA_NON_ESPOSTA("Fascia non esposta", 250),
    OPERATORE_NON_DICHIARATO_1("Operatore non dichiarato", 200),
    OPERATORE_NON_DICHIARATO_2("Operatore non dichiarato", 800),
    INTERFERENZA_ARBITRALE("Interferenza arbitrale", 500),
    COMPORTAMENTO_ANTISPORTIVO("Comportamento antisportivo", 500),
    ASG_OVERJOULE("ASG overjoule", 700),
    MARCATURA_ASG_ASSENTE("Marcatura ASG assente", 150);

    private final String label;
    private final Integer punteggioNegativo;

    InfrazioneEnum(String label, Integer punteggioNegativo) {
        this.label = label;
        this.punteggioNegativo = punteggioNegativo;
    }

    public String getLabel() {
        return label;
    }

    public Integer getPunteggioNegativo() {
        return punteggioNegativo;
    }

    public static Optional<InfrazioneEnum> fromString(String text) {
        return Arrays.stream(InfrazioneEnum.values())
                .filter(e -> e.name().equalsIgnoreCase(text))
                .findFirst();
    }
}