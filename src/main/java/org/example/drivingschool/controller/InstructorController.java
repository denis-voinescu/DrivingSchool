package org.example.drivingschool.controller;

import org.example.drivingschool.service.InstructorService;
import org.openapitools.api.InstructorsApi;
import org.openapitools.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/instructors")
public class InstructorController implements InstructorsApi {

    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @Override
    public ResponseEntity<Instructor> createInstructor(InstructorCreate instructorCreate) {
        return null;
    }

    @Override
    public ResponseEntity<Instructor> getInstructorById(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<List<Enrollment>> listInstructorEnrollments(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<List<Exam>> listInstructorExams(Integer id, LocalDate from, LocalDate to) {
        return null;
    }

    @Override
    public ResponseEntity<List<Lesson>> listInstructorLessons(Integer id, LocalDate date) {
        return null;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Instructor>> listInstructors() {
        return ResponseEntity.ok(instructorService.list());
    }

    @Override
    public ResponseEntity<Instructor> updateInstructor(Integer id, InstructorUpdate instructorUpdate) {
        return null;
    }
}
