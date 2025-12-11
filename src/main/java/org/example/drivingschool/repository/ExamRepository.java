package org.example.drivingschool.repository;

import org.example.drivingschool.model.EnrollmentEntity;
import org.example.drivingschool.model.ExamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<ExamEntity, Integer> {
    List<ExamEntity> findByEnrollment(EnrollmentEntity enrollment);
}
