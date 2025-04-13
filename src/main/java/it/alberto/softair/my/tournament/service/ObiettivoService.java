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

    public Optional<Obiettivo> findById(Integer id) {
        return obiettivoRepository.findById(id);
    }

    public List<Obiettivo> findByFaseE(Boolean faseE) {
        return obiettivoRepository.findByFaseE(faseE);
    }

    public Obiettivo save(Obiettivo obiettivo) {
        return obiettivoRepository.save(obiettivo);
    }

    public void deleteById(Integer id) {
        obiettivoRepository.deleteById(id);
    }
}