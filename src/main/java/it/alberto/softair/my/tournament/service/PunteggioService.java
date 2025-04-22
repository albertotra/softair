package it.alberto.softair.my.tournament.service;

import it.alberto.softair.my.tournament.entity.Obiettivo;
import it.alberto.softair.my.tournament.entity.Punteggio;
import it.alberto.softair.my.tournament.repository.PunteggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PunteggioService {

    @Autowired
    private PunteggioRepository punteggioRepository;

    public List<Obiettivo> getByTorneoId(Integer idTorneo) {
        return punteggioRepository.findByTorneo_id(idTorneo);
    }

    public Punteggio getById(Integer id) {
        Optional<Punteggio> punteggio = punteggioRepository.findById(id);
        if (punteggio.isPresent()) {
            return punteggio.get();
        } else {
            return null;
        }
    }
}
