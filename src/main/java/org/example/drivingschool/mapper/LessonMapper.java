package org.example.drivingschool.mapper;

import org.example.drivingschool.model.LessonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.openapitools.model.Lesson;
import org.openapitools.model.LessonCreate;
import org.openapitools.model.LessonUpdate;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface LessonMapper {

    @Mapping(target = "enrollment", ignore = true)
    LessonEntity toEntity(LessonCreate dto);

    @Mapping(source = "enrollment.id", target = "enrollmentId")
    Lesson toDto(LessonEntity entity);

    void updateEntity(@MappingTarget LessonEntity entity, LessonUpdate dto);

    default OffsetDateTime map(Instant value) {
        return value == null ? null : value.atOffset(ZoneOffset.UTC);
    }

    default Instant map(OffsetDateTime value) {
        return value == null ? null : value.toInstant();
    }
}
