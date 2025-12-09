package org.example.drivingschool.mapper;

import org.example.drivingschool.model.LessonEntity;
import org.mapstruct.Mapper;
import org.openapitools.model.Lesson;
import org.openapitools.model.LessonCreate;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring")
public interface LessonMapper {

    LessonEntity toEntity(LessonCreate dto);
    Lesson toDto(LessonEntity entity);

    default OffsetDateTime map(Instant value) {
        return value == null ? null : value.atOffset(ZoneOffset.UTC);
    }

    default Instant map(OffsetDateTime value) {
        return value == null ? null : value.toInstant();
    }
}
