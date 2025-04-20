package it.alberto.softair.my.tournament.service;

import it.alberto.softair.my.tournament.dto.DettaglioTorneoSquadraDto;
import it.alberto.softair.my.tournament.dto.TorneoSquadraDto;
import it.alberto.softair.my.tournament.dto.mapper.TorneoSquadraMapper;
import it.alberto.softair.my.tournament.repository.SquadraRepository;
import it.alberto.softair.my.tournament.repository.TorneoSquadraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TorneoSquadraService {

    @Autowired
    private TorneoSquadraRepository torneoSquadraRepository;

    @Autowired
    private TorneoSquadraMapper torneoSquadraMapper;

    @Autowired
    private SquadraRepository squadraRepository;

    public List<DettaglioTorneoSquadraDto> getByTorneoId(Integer torneoId) {
        List<TorneoSquadraDto> result = torneoSquadraRepository.findByIdTorneo(torneoId).stream().map(torneoSquadraMapper::toDto).toList();
        List<DettaglioTorneoSquadraDto> response = new ArrayList<>();

        if (!result.isEmpty()) {
            for (TorneoSquadraDto torneoSquadraDto: result) {
                response.add(createDettaglioTorneoSquadra(torneoSquadraDto));
            }
        }

        return response;
    }

    private DettaglioTorneoSquadraDto createDettaglioTorneoSquadra(TorneoSquadraDto entity) {
        DettaglioTorneoSquadraDto dto = new DettaglioTorneoSquadraDto();
        dto.setId(entity.getId());
        dto.setIdTorneo(entity.getIdTorneo());
        dto.setIdSquadra(entity.getIdSquadra());
        dto.setCallSign(entity.getCallSign());
        dto.setRadioCh(entity.getRadioCh());
        dto.setOraTestAsg(entity.getOraTestAsg());
        dto.setOraIncursione(entity.getOraIncursione());
        dto.setNomeSquadra(squadraRepository.findById(entity.getId()).get().getNome());
        return dto;
    }


}
