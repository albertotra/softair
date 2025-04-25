package it.alberto.softair.my.tournament.service;

import it.alberto.softair.my.tournament.entity.Obiettivo;
import it.alberto.softair.my.tournament.entity.PunteggioSquadra;
import it.alberto.softair.my.tournament.repository.ObiettivoRepository;
import it.alberto.softair.my.tournament.repository.PunteggioSquadraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObiettivoService {

    @Autowired
    private ObiettivoRepository obiettivoRepository;

    @Autowired
    private PunteggioSquadraRepository punteggioSquadraRepository;

    public List<Obiettivo> findAll() {
        return obiettivoRepository.findAll();
    }

    public List<Obiettivo> findByIdTorneo(Integer idTorneo, Integer idSquadra) {
        List<Obiettivo> obiettivi = obiettivoRepository.findByIdTorneo(idTorneo);

        for (Obiettivo obiettivo: obiettivi) {
            PunteggioSquadra punteggioSquadra = punteggioSquadraRepository.findBySquadra_idAndTorneo_idAndPunteggio_id(idSquadra, obiettivo.getIdTorneo(), obiettivo.getPunteggi().get(0).getId());
            if (punteggioSquadra != null) {
                obiettivo.setPunteggioCalcolato(true);
                obiettivo.setPunteggio(punteggioSquadra.getTotale());
            } else {
                obiettivo.setPunteggioCalcolato(false);
            }
        }

        return obiettivi;
    }

    public Obiettivo findByIdObiettivo(Integer idObiettivo) {
        Optional<Obiettivo> obiettivo = obiettivoRepository.findById(idObiettivo);
        if (obiettivo.isPresent()) {
            return obiettivo.get();
        } else {
            return null;
        }
    }

}