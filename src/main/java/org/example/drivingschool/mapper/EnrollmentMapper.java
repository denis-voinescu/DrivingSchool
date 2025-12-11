package org.example.drivingschool.mapper;

import org.example.drivingschool.model.EnrollmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.openapitools.model.Enrollment;
import org.openapitools.model.EnrollmentCreate;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EnrollmentMapper {

  @Mapping(target = "student", ignore = true)
  @Mapping(target = "instructor", ignore = true)
  @Mapping(target = "vehicle", ignore = true)
  EnrollmentEntity toEntity(EnrollmentCreate dto);

  @Mapping(source = "student.id", target = "studentId")
  @Mapping(source = "instructor.id", target = "instructorId")
  @Mapping(source = "vehicle.id", target = "vehicleId")
  Enrollment toDto(EnrollmentEntity entity);

  default OffsetDateTime map(Instant value) {
    return value == null ? null : value.atOffset(ZoneOffset.UTC);
  }

  default Instant map(OffsetDateTime value) {
    return value == null ? null : value.toInstant();
  }
}
