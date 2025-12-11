package org.example.drivingschool.controller;

import jakarta.validation.Valid;
import org.example.drivingschool.service.InstructorService;
import org.example.drivingschool.service.LessonService;
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
    private final LessonService lessonService;

    public InstructorController(InstructorService instructorService, LessonService lessonService) {
        this.instructorService = instructorService;
        this.lessonService = lessonService;
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
    @GetMapping({"/{id}/lessons"})
    public ResponseEntity<List<Lesson>> listInstructorLessons(Integer id, LocalDate date) {
        return ResponseEntity.ok(lessonService.listForInstructor(id, date));
    }


    @Override
    @GetMapping
    public ResponseEntity<List<Instructor>> listInstructors() {
        return ResponseEntity.ok(instructorService.list());
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<Instructor> updateInstructor(@PathVariable Integer id, InstructorUpdate instructorUpdate) {
        return ResponseEntity.ok(instructorService.update(id, instructorUpdate));
    }
}
