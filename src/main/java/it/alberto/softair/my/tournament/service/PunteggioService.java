package it.alberto.softair.my.tournament.service;

import it.alberto.softair.my.tournament.dto.PenalitaDto;
import it.alberto.softair.my.tournament.dto.PunteggioEInputDto;
import it.alberto.softair.my.tournament.dto.PunteggioInputDto;
import it.alberto.softair.my.tournament.dto.SalvaPunteggioResponseDto;
import it.alberto.softair.my.tournament.entity.*;
import it.alberto.softair.my.tournament.enumeration.InfrazioneEnum;
import it.alberto.softair.my.tournament.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class PunteggioService {

    @Autowired
    private PunteggioRepository punteggioRepository;

    @Autowired
    private PunteggioSquadraRepository punteggioSquadraRepository;

    @Autowired
    private TorneoRepository torneoRepository;

    @Autowired
    private SquadraRepository squadraRepository;

    @Autowired
    private PunteggioSquadraDettaglioRepository punteggioSquadraDettaglioRepository;

    @Autowired
    private ClassificaRepository classificaRepository;

    public List<Obiettivo> getByTorneoId(Integer idTorneo) {
        return punteggioRepository.findByTorneo_id(idTorneo);
    }

    public Punteggio getById(Integer id) {
        Optional<Punteggio> punteggio = punteggioRepository.findById(id);
        return punteggio.orElse(null);
    }

    public SalvaPunteggioResponseDto salvaPunteggio(PunteggioInputDto dto) {
        SalvaPunteggioResponseDto response = new SalvaPunteggioResponseDto();
        Optional<Punteggio> punteggio = punteggioRepository.findById(dto.getIdPunteggio());
        PunteggioSquadra punteggioSquadraSaved = punteggioSquadraRepository.findBySquadra_idAndTorneo_idAndPunteggio_id(dto.getIdSquadra(), punteggio.get().getTorneo().getId(), dto.getIdPunteggio());
        Punteggio punteggioEntity = punteggio.get();
        response.setIdSquadra(dto.getIdSquadra());
        response.setIdTorneo(punteggio.get().getTorneo().getId());

        Optional<Squadra> squadraOptional = squadraRepository.findById(dto.getIdSquadra());
        if (punteggioSquadraSaved == null) {
            Integer punteggioFinale = 0;
            if (dto.isFuoriFinestra()) {
                List<PunteggioE> punteggioEList = punteggioEntity.getPunteggiE();
                for (PunteggioE punteggioE : punteggioEList) {
                    punteggioFinale -= punteggioE.getValore();
                }

                if (punteggioEntity.getDifensori()) {
                    punteggioFinale -= punteggioEntity.getNumeroDifensori() * 40;
                }
                if (punteggioEntity.getRibelli()) {
                    punteggioFinale -= punteggioEntity.getNumeroRibelli() * 30;
                }

                PunteggioSquadra punteggioSquadra = new PunteggioSquadra();
                punteggioSquadra.setPunteggio(punteggioEntity);
                punteggioSquadra.setTorneo(punteggioEntity.getTorneo());
                punteggioSquadra.setTotale(punteggioFinale);

                punteggioSquadra.setSquadra(squadraOptional.get());

                PunteggioSquadra savedPunteggioSquadra = punteggioSquadraRepository.save(punteggioSquadra);

                PunteggioSquadraDettaglio punteggioSquadraDettaglio = new PunteggioSquadraDettaglio();
                punteggioSquadraDettaglio.setChiave("FUORI_FINESTRA");
                punteggioSquadraDettaglio.setValore(punteggioFinale.toString());
                punteggioSquadraDettaglio.setPunteggioSquadra(savedPunteggioSquadra);

                punteggioSquadraDettaglioRepository.save(punteggioSquadraDettaglio);
            } else {
                PunteggioSquadra punteggioSquadra = new PunteggioSquadra();

                List<PunteggioSquadraDettaglio> punteggioSquadraDettaglioList = new ArrayList<>();
                if (dto.getNumeroRibelli() != null) {
                    punteggioFinale += dto.getNumeroRibelli() * 30;
                    PunteggioSquadraDettaglio punteggioSquadraDettaglio = new PunteggioSquadraDettaglio();
                    punteggioSquadraDettaglio.setChiave("RIBELLI_ELIMINATI");
                    punteggioSquadraDettaglio.setValore(String.valueOf(dto.getNumeroRibelli() * 30));
                    punteggioSquadraDettaglioList.add(punteggioSquadraDettaglio);
                }

                if (dto.getNumeroDifensori() != null) {
                    punteggioFinale += dto.getNumeroDifensori() * 40;
                    PunteggioSquadraDettaglio punteggioSquadraDettaglio = new PunteggioSquadraDettaglio();
                    punteggioSquadraDettaglio.setChiave("DIFENSORI_ELIMINATI");
                    punteggioSquadraDettaglio.setValore(String.valueOf(dto.getNumeroDifensori() * 40));
                    punteggioSquadraDettaglioList.add(punteggioSquadraDettaglio);
                }
                if (dto.getNumeroCivili() != null) {
                    punteggioFinale -= dto.getNumeroCivili() * 25;
                    PunteggioSquadraDettaglio punteggioSquadraDettaglio = new PunteggioSquadraDettaglio();
                    punteggioSquadraDettaglio.setChiave("CIVILI_ELIMINATI");
                    punteggioSquadraDettaglio.setValore(String.valueOf(dto.getNumeroCivili() * 25));
                    punteggioSquadraDettaglioList.add(punteggioSquadraDettaglio);
                }

                if (!dto.getPunteggiE().isEmpty()) {
                    for (PunteggioEInputDto punteggioE : dto.getPunteggiE()) {
                        if (punteggioE.isSelezionato()) {
                            punteggioFinale += punteggioE.getValore();
                            PunteggioSquadraDettaglio punteggioSquadraDettaglio = new PunteggioSquadraDettaglio();
                            punteggioSquadraDettaglio.setChiave("FASE_E");
                            punteggioSquadraDettaglio.setValore(punteggioE.getDescrizione() + "|" + punteggioE.getValore());
                            punteggioSquadraDettaglioList.add(punteggioSquadraDettaglio);
                        }
                    }
                }

                if (dto.getMinutiImpiegati() != null) {
                    Integer minutiTotaliObiettivo = punteggio.get().getObiettivo().getDurata();
                    Integer minutiRisparmiati = minutiTotaliObiettivo - dto.getMinutiImpiegati();

                    if (minutiRisparmiati > 0) {
                        boolean fasiECompletate = dto.getPunteggiE().stream().allMatch(PunteggioEInputDto::isSelezionato);
                        boolean allRibelli = false;
                        boolean allDifensori = false;

                        if(punteggioEntity.getRibelli()) {
                            if(Objects.equals(dto.getNumeroRibelli(), punteggioEntity.getNumeroRibelli())) {
                                allRibelli = true;
                            }
                        } else {
                            allRibelli = true;
                        }

                        if(punteggioEntity.getDifensori()) {
                            if(Objects.equals(dto.getNumeroDifensori(), punteggioEntity.getNumeroDifensori())) {
                                allDifensori = true;
                            }
                        } else {
                            allDifensori = true;
                        }

                        if (fasiECompletate && allRibelli && allDifensori) {
                            punteggioFinale += minutiRisparmiati * 10;

                            PunteggioSquadraDettaglio punteggioSquadraDettaglio = new PunteggioSquadraDettaglio();
                            punteggioSquadraDettaglio.setChiave("MINUTI_RISPARMIATI");
                            punteggioSquadraDettaglio.setValore(String.valueOf(minutiRisparmiati * 10));
                            punteggioSquadraDettaglioList.add(punteggioSquadraDettaglio);
                        }
                    }
                }

                if (!dto.getPenalita().isEmpty()) {
                    for (PenalitaDto penalita : dto.getPenalita()) {
                        Optional<InfrazioneEnum> infrOptional = InfrazioneEnum.fromString(penalita.getSelectValue());
                        if (infrOptional.isPresent()) {
                            InfrazioneEnum infrazione = infrOptional.get();
                            punteggioFinale -= infrazione.getPunteggioNegativo();
                            PunteggioSquadraDettaglio punteggioSquadraDettaglio = new PunteggioSquadraDettaglio();
                            punteggioSquadraDettaglio.setChiave(infrazione.name());
                            punteggioSquadraDettaglio.setValore(String.valueOf(infrazione.getPunteggioNegativo()));
                            punteggioSquadraDettaglioList.add(punteggioSquadraDettaglio);
                        } else {
                            if (penalita.getInputValue() != null && penalita.getSelectValue() != null && !penalita.getSelectValue().isEmpty()) {
                                try {
                                    punteggioFinale -= Integer.parseInt(penalita.getInputValue());
                                } catch (NumberFormatException e) {
                                    throw new RuntimeException(e);
                                }

                                PunteggioSquadraDettaglio punteggioSquadraDettaglio = new PunteggioSquadraDettaglio();
                                punteggioSquadraDettaglio.setChiave(penalita.getSelectValue());
                                punteggioSquadraDettaglio.setValore(penalita.getInputValue());
                                punteggioSquadraDettaglioList.add(punteggioSquadraDettaglio);
                            }
                        }
                    }
                }


                punteggioSquadra.setPunteggio(punteggioEntity);
                punteggioSquadra.setTorneo(punteggioEntity.getTorneo());
                punteggioSquadra.setTotale(punteggioFinale);

                if (dto.getContestazione() != null) {
                    punteggioSquadra.setContestazione(dto.getContestazione());
                }

                if (dto.getNote() != null) {
                    punteggioSquadra.setNote(dto.getNote());
                }

                if (dto.getInizioObj() != null) {
                    punteggioSquadra.setInizioObj(dto.getInizioObj());
                }

                if (dto.getFineObj() != null) {
                    punteggioSquadra.setFineObj(dto.getFineObj());
                }

                if (dto.getMinutiImpiegati() != null) {
                    punteggioSquadra.setMinImpiegati(dto.getMinutiImpiegati());
                }

                punteggioSquadra.setSquadra(squadraOptional.get());

                PunteggioSquadra savedPunteggioSquadra = punteggioSquadraRepository.save(punteggioSquadra);

                if (!punteggioSquadraDettaglioList.isEmpty()) {
                    for (PunteggioSquadraDettaglio punteggioSquadraDettaglio : punteggioSquadraDettaglioList) {
                        punteggioSquadraDettaglio.setPunteggioSquadra(savedPunteggioSquadra);
                        punteggioSquadraDettaglioRepository.save(punteggioSquadraDettaglio);
                    }
                }

            }

            Classifica classifica = classificaRepository.findBySquadra_id(dto.getIdSquadra());
            if (classifica != null) {
                classifica.setPunteggio(classifica.getPunteggio() + punteggioFinale);
                classificaRepository.save(classifica);
            } else {
                classifica = new Classifica();
                classifica.setTorneo(punteggioEntity.getTorneo());
                classifica.setSquadra(squadraOptional.get());
                classifica.setDataInserimento(LocalDateTime.now());
                classifica.setPunteggio(punteggioFinale);
                classificaRepository.save(classifica);
            }
            return response;

        } else {
            throw new RuntimeException("E stato gia salvato un punteggio per questa squadra in questo torneo");
        }

    }
}
