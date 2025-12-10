package org.example.drivingschool.mapper;

import org.example.drivingschool.model.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.openapitools.model.Person;
import org.openapitools.model.PersonCreate;
import org.openapitools.model.PersonUpdate;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PersonMapper {

    PersonEntity toEntity(PersonCreate dto);

    Person toDto(PersonEntity entity);

    void updateEntity(@MappingTarget PersonEntity entity, PersonUpdate dto);

    default OffsetDateTime map(Instant value) {
        return value == null ? null : value.atOffset(ZoneOffset.UTC);
    }

    default Instant map(OffsetDateTime value) {
        return value == null ? null : value.toInstant();
    }
}
