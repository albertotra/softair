package it.alberto.softair.my.tournament.service;

import it.alberto.softair.my.tournament.entity.Classifica;
import it.alberto.softair.my.tournament.entity.PunteggioSquadra;
import it.alberto.softair.my.tournament.repository.ClassificaRepository;
import it.alberto.softair.my.tournament.repository.PunteggioSquadraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassificaService {
    @Autowired
    private ClassificaRepository classificaRepository;

    @Autowired
    private PunteggioSquadraRepository punteggioSquadraRepository;

    public List<Classifica> calcolaClassifica(Integer idTorneo) {
        List<PunteggioSquadra> punteggioSquadraList = punteggioSquadraRepository.findByTorneo_id(idTorneo);

        for (PunteggioSquadra punteggioSquadra: punteggioSquadraList) {
            System.out.println(punteggioSquadra);
        }

        return null;
    }

    public List<Classifica> getAllByIdTorneo(Integer idTorneo) {
        return classificaRepository.findByTorneo_idOrderByPunteggioDesc(idTorneo);
    }


}
