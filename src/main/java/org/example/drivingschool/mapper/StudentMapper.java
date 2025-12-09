package org.example.drivingschool.mapper;

import org.example.drivingschool.model.StudentEntity;
import org.mapstruct.Mapper;
import org.openapitools.model.Student;
import org.openapitools.model.StudentCreate;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentEntity toEntity(StudentCreate dto);
    Student toDto(StudentEntity entity);

    default OffsetDateTime map(Instant value) {
        return value == null ? null : value.atOffset(ZoneOffset.UTC);
    }

    default Instant map(OffsetDateTime value) {
        return value == null ? null : value.toInstant();
    }
}
