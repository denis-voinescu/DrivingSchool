package org.example.drivingschool.repository;

import org.example.drivingschool.model.EnrollmentEntity;
import org.example.drivingschool.model.InstructorEntity;
import org.example.drivingschool.model.LessonEntity;
import org.example.drivingschool.model.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<LessonEntity, Integer> {
    Integer countByEnrollment(EnrollmentEntity enrollment);
    List<LessonEntity> findByEnrollmentInstructor(InstructorEntity instructor);
    List<LessonEntity> findByEnrollmentInstructorAndDate(InstructorEntity instructor, LocalDate date);
    List<LessonEntity> findByEnrollmentStudent(StudentEntity student);

    List<LessonEntity> findByEnrollmentStudentAndCompleted(StudentEntity student, Boolean completed);
}
