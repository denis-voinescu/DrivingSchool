package org.example.drivingschool.repository;

import org.example.drivingschool.model.InstructorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends JpaRepository<InstructorEntity, Integer> {}
