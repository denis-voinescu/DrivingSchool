package org.example.drivingschool.controller;

import jakarta.validation.Valid;
import org.example.drivingschool.service.InstructorService;
import org.openapitools.api.InstructorsApi;
import org.openapitools.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping
    public ResponseEntity<Instructor> createInstructor(@Valid @RequestBody InstructorCreate instructorCreate) {
        return ResponseEntity.ok(instructorService.create(instructorCreate));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Instructor> getInstructorById(@PathVariable Integer id) {
        return ResponseEntity.ok(instructorService.getById(id));

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
    @PatchMapping("/{id}")
    public ResponseEntity<Instructor> updateInstructor(Integer id, InstructorUpdate instructorUpdate) {
        return ResponseEntity.ok(instructorService.update(id, instructorUpdate));
    }
}
