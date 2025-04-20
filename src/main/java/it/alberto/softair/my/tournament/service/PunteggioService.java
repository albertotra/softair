package it.alberto.softair.my.tournament.service;

import it.alberto.softair.my.tournament.entity.Obiettivo;
import it.alberto.softair.my.tournament.repository.PunteggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PunteggioService {

    @Autowired
    private PunteggioRepository punteggioRepository;

    public List<Obiettivo> getByTorneoId(Integer idTorneo) {
        return punteggioRepository.findByTorneo_id(idTorneo);
    }
}
