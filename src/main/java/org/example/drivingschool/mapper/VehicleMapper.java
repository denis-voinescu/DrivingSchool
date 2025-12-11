package org.example.drivingschool.mapper;

import org.example.drivingschool.model.VehicleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.openapitools.model.Vehicle;
import org.openapitools.model.VehicleCreate;
import org.openapitools.model.VehicleUpdate;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface VehicleMapper {

  VehicleEntity toEntity(VehicleCreate dto);

  Vehicle toDto(VehicleEntity entity);

  void updateEntity(@MappingTarget VehicleEntity entity, VehicleUpdate dto);

  default OffsetDateTime map(Instant value) {
    return value == null ? null : value.atOffset(ZoneOffset.UTC);
  }

  default Instant map(OffsetDateTime value) {
    return value == null ? null : value.toInstant();
  }
}
