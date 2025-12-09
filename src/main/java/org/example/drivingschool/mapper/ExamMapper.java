package org.example.drivingschool.mapper;

import org.example.drivingschool.model.ExamEntity;
import org.mapstruct.Mapper;
import org.openapitools.model.Exam;
import org.openapitools.model.ExamCreate;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring")
public interface ExamMapper {

    ExamEntity toEntity(ExamCreate dto);
    Exam toDto(ExamEntity entity);

    default OffsetDateTime map(Instant value) {
        return value == null ? null : value.atOffset(ZoneOffset.UTC);
    }

    default Instant map(OffsetDateTime value) {
        return value == null ? null : value.toInstant();
    }
}
