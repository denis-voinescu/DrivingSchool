package org.example.drivingschool.mapper;

import org.example.drivingschool.model.StudentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.openapitools.model.Student;
import org.openapitools.model.StudentCreate;
import org.openapitools.model.StudentUpdate;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface StudentMapper {

    @Mapping(target = "person", ignore = true)
    StudentEntity toEntity(StudentCreate dto);

    @Mapping(source = "person.id", target = "personId")
    Student toDto(StudentEntity entity);

    void updateEntity(@MappingTarget StudentEntity entity, StudentUpdate dto);

    default OffsetDateTime map(Instant value) {
        return value == null ? null : value.atOffset(ZoneOffset.UTC);
    }

    default Instant map(OffsetDateTime value) {
        return value == null ? null : value.toInstant();
    }
}
