package it.alberto.softair.my.tournament.dto.mapper;

import it.alberto.softair.my.tournament.dto.TorneoSquadraDto;
import it.alberto.softair.my.tournament.entity.TorneoSquadra;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TorneoSquadraMapper {

    TorneoSquadraMapper INSTANCE = Mappers.getMapper(TorneoSquadraMapper.class);

    TorneoSquadraDto toDto(TorneoSquadra torneoSquadra);
}
