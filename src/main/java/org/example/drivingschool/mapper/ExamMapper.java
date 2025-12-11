package org.example.drivingschool.mapper;

import org.example.drivingschool.model.ExamEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.openapitools.model.Exam;
import org.openapitools.model.ExamCreate;
import org.openapitools.model.ExamUpdate;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ExamMapper {

  ExamEntity toEntity(ExamCreate dto);

  Exam toDto(ExamEntity entity);

  void updateEntity(@MappingTarget ExamEntity entity, ExamUpdate dto);

  List<Exam> toDtoList(List<ExamEntity> entities);

  default OffsetDateTime map(Instant value) {
    return value == null ? null : value.atOffset(ZoneOffset.UTC);
  }

  default Instant map(OffsetDateTime value) {
    return value == null ? null : value.toInstant();
  }
}
