package it.alberto.softair.my.tournament.service;

import it.alberto.softair.my.tournament.entity.Torneo;
import it.alberto.softair.my.tournament.repository.TorneoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TorneoService {

    @Autowired
    private TorneoRepository torneoRepository;

    public List<Torneo> findAll() {
        return torneoRepository.findAll();
    }

    public Optional<Torneo> findById(Integer id) {
        return torneoRepository.findById(id);
    }

    public List<Torneo> findByNomeContaining(String nome) {
        return torneoRepository.findByNomeContaining(nome);
    }

    public List<Torneo> findByDataInizioAfter(LocalDateTime data) {
        return torneoRepository.findByDataInizioAfter(data);
    }

    public List<Torneo> findByLuogoContaining(String luogo) {
        return torneoRepository.findByLuogoContaining(luogo);
    }

    public Torneo save(Torneo torneo) {
        return torneoRepository.save(torneo);
    }

    public void deleteById(Integer id) {
        torneoRepository.deleteById(id);
    }
}