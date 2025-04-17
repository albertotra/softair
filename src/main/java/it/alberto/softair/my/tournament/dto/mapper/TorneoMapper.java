package it.alberto.softair.my.tournament.dto.mapper;

import it.alberto.softair.my.tournament.dto.TorneoDto;
import it.alberto.softair.my.tournament.entity.Torneo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TorneoMapper {

    TorneoMapper INSTANCE = Mappers.getMapper(TorneoMapper.class);

    TorneoDto toDto(Torneo torneo);
}
