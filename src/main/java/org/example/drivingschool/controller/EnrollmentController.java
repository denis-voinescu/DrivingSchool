package org.example.drivingschool.controller;

import org.example.drivingschool.service.EnrollmentService;
import org.openapitools.api.EnrollmentsApi;
import org.openapitools.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/enrollments")
public class EnrollmentController implements EnrollmentsApi {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Enrollment>> listEnrollments() {
        return ResponseEntity.ok(enrollmentService.list());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable Integer id) {
        return ResponseEntity.ok(enrollmentService.getById(id));
    }

    @Override
    @PostMapping
    public ResponseEntity<Enrollment> createEnrollment(EnrollmentCreate enrollmentCreate) {
        return ResponseEntity.ok(enrollmentService.create(enrollmentCreate));
    }

    @Override
    public ResponseEntity<Exam> createExamForEnrollment(Integer id, ExamCreate examCreate) {
        return null;
    }

    @Override
    public ResponseEntity<Lesson> createLessonForEnrollment(Integer id, LessonCreate lessonCreate) {
        return null;
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<Enrollment> updateEnrollment(@PathVariable Integer id, EnrollmentUpdate enrollmentUpdate) {
        return ResponseEntity.ok(enrollmentService.update(id, enrollmentUpdate));
    }
}
