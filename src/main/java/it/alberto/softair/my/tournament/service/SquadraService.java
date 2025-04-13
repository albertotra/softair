package it.alberto.softair.my.tournament.service;

import it.alberto.softair.my.tournament.entity.Squadra;
import it.alberto.softair.my.tournament.repository.SquadraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SquadraService {

    @Autowired
    private SquadraRepository squadraRepository;

    public List<Squadra> findAll() {
        return squadraRepository.findAll();
    }

    public Optional<Squadra> findById(Integer id) {
        return squadraRepository.findById(id);
    }

    public List<Squadra> findByNomeContaining(String nome) {
        return squadraRepository.findByNomeContaining(nome);
    }

    public List<Squadra> findByComitato(String comitato) {
        return squadraRepository.findByComitato(comitato);
    }

    public Squadra save(Squadra squadra) {
        return squadraRepository.save(squadra);
    }

    public void deleteById(Integer id) {
        squadraRepository.deleteById(id);
    }
}