package it.alberto.softair.my.tournament.service;

import it.alberto.softair.my.tournament.dto.DettaglioTorneoSquadraDto;
import it.alberto.softair.my.tournament.dto.TorneoSquadraDto;
import it.alberto.softair.my.tournament.dto.mapper.TorneoSquadraMapper;
import it.alberto.softair.my.tournament.repository.ObiettivoRepository;
import it.alberto.softair.my.tournament.repository.PunteggioSquadraRepository;
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

    @Autowired
    private ObiettivoRepository obiettivoRepository;

    @Autowired
    private PunteggioSquadraRepository punteggioSquadraRepository;

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

    private DettaglioTorneoSquadraDto createDettaglioTorneoSquadra(TorneoSquadraDto torneoSquadraDto) {
        DettaglioTorneoSquadraDto dto = new DettaglioTorneoSquadraDto();
        dto.setId(torneoSquadraDto.getId());
        dto.setIdTorneo(torneoSquadraDto.getIdTorneo());
        dto.setIdSquadra(torneoSquadraDto.getIdSquadra());
        dto.setCallSign(torneoSquadraDto.getCallSign());
        dto.setRadioCh(torneoSquadraDto.getRadioCh());
        dto.setOraTestAsg(torneoSquadraDto.getOraTestAsg());
        dto.setOraIncursione(torneoSquadraDto.getOraIncursione());
        dto.setNomeSquadra(squadraRepository.findById(torneoSquadraDto.getIdSquadra()).get().getNome());
        dto.setObiettiviCompletati(punteggioSquadraRepository.countBySquadra_idAndTorneo_id(dto.getIdSquadra(), dto.getIdTorneo()));
        dto.setObiettiviTotali(obiettivoRepository.countByIdTorneo(torneoSquadraDto.getIdTorneo()));
        return dto;
    }


}
