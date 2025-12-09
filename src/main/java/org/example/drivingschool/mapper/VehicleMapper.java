package org.example.drivingschool.mapper;

import org.example.drivingschool.model.VehicleEntity;
import org.mapstruct.Mapper;
import org.openapitools.model.Vehicle;
import org.openapitools.model.VehicleCreate;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring")
public interface VehicleMapper {

    VehicleEntity toEntity(VehicleCreate dto);
    Vehicle toDto(VehicleEntity entity);

    default OffsetDateTime map(Instant value) {
        return value == null ? null : value.atOffset(ZoneOffset.UTC);
    }

    default Instant map(OffsetDateTime value) {
        return value == null ? null : value.toInstant();
    }
}
