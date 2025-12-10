package org.example.drivingschool.mapper;

import org.example.drivingschool.model.InstructorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.openapitools.model.Instructor;
import org.openapitools.model.InstructorCreate;
import org.openapitools.model.InstructorUpdate;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface InstructorMapper {

    @Mapping(target = "person", ignore = true)
    InstructorEntity toEntity(InstructorCreate dto);

    @Mapping(source = "person.id", target = "personId")
    Instructor toDto(InstructorEntity entity);

    void updateEntity(@MappingTarget InstructorEntity entity, InstructorUpdate dto);

    default OffsetDateTime map(Instant value) {
        return value == null ? null : value.atOffset(ZoneOffset.UTC);
    }

    default Instant map(OffsetDateTime value) {
        return value == null ? null : value.toInstant();
    }
}
