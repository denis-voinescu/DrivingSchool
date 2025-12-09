package org.example.drivingschool.mapper;

import org.example.drivingschool.model.EnrollmentEntity;
import org.mapstruct.Mapper;
import org.openapitools.model.Enrollment;
import org.openapitools.model.EnrollmentCreate;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {

    EnrollmentEntity toEntity(EnrollmentCreate dto);
    Enrollment toDto(EnrollmentEntity entity);

    default OffsetDateTime map(Instant value) {
        return value == null ? null : value.atOffset(ZoneOffset.UTC);
    }

    default Instant map(OffsetDateTime value) {
        return value == null ? null : value.toInstant();
    }
}
