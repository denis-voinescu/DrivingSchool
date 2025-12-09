package org.example.drivingschool.repository;

import org.example.drivingschool.model.ExamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<ExamEntity, Integer> {
}
