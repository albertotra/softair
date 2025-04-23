package it.alberto.softair.my.tournament.service;

import it.alberto.softair.my.tournament.entity.Obiettivo;
import it.alberto.softair.my.tournament.repository.ObiettivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObiettivoService {

    @Autowired
    private ObiettivoRepository obiettivoRepository;

    public List<Obiettivo> findAll() {
        return obiettivoRepository.findAll();
    }

    public List<Obiettivo> findByIdTorneo(Integer idTorneo) {
        return obiettivoRepository.findByIdTorneo(idTorneo);
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