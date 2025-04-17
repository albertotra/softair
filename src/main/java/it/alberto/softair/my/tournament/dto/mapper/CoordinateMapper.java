package it.alberto.softair.my.tournament.dto.mapper;

import it.alberto.softair.my.tournament.dto.CoordinateDto;
import it.alberto.softair.my.tournament.entity.Coordinate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CoordinateMapper {
    CoordinateDto toDto(Coordinate coordinate);
}
