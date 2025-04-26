package it.alberto.softair.my.tournament.service;

import it.alberto.softair.my.tournament.entity.PunteggioSquadra;
import it.alberto.softair.my.tournament.repository.PunteggioSquadraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PunteggioSquadraService {

    @Autowired
    private PunteggioSquadraRepository punteggioSquadraRepository;



    public List<PunteggioSquadra> getPunteggioSquadraBySquadraIdAndTorneoId(Integer idSquadra, Integer idTorneo) {
        List<PunteggioSquadra> punteggi = punteggioSquadraRepository.findBySquadra_idAndTorneo_id(idSquadra, idTorneo);

        for(PunteggioSquadra punteggioSquadra: punteggi) {

        }

        return punteggi;
    }
}
