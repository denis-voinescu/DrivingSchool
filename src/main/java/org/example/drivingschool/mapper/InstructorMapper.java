package org.example.drivingschool.mapper;

import org.example.drivingschool.model.InstructorEntity;
import org.mapstruct.Mapper;
import org.openapitools.model.Instructor;
import org.openapitools.model.InstructorCreate;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring")
public interface InstructorMapper {

    InstructorEntity toEntity(InstructorCreate dto);
    Instructor toDto(InstructorEntity entity);

    default OffsetDateTime map(Instant value) {
        return value == null ? null : value.atOffset(ZoneOffset.UTC);
    }

    default Instant map(OffsetDateTime value) {
        return value == null ? null : value.toInstant();
    }
}
