package org.example.drivingschool.repository;

import org.example.drivingschool.model.EnrollmentEntity;
import org.example.drivingschool.model.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<EnrollmentEntity, Integer> {
  List<EnrollmentEntity> findByStudent(StudentEntity student);
}
