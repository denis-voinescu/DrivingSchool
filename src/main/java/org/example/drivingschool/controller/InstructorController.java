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

  // Create an instructor by passing an "InstructorCreate" DTO in the request body
  // public InstructorCreate(Integer personId,
  //                        LicenseCategory licenseCategory,
  //                        @Nullable Boolean isCompliant,
  //                        @Nullable Boolean isActive)

  @Override
  @PostMapping
  public ResponseEntity<Instructor> createInstructor(
      @Valid @RequestBody InstructorCreate instructorCreate) {
    return ResponseEntity.ok(instructorService.create(instructorCreate));
  }

  // Get a specific instructor by passing their ID as a path variable

  @Override
  @GetMapping("/{id}")
  public ResponseEntity<Instructor> getInstructorById(@PathVariable Integer id) {
    return ResponseEntity.ok(instructorService.getById(id));
  }

  // Get all lessons for a specific instructor by passing their ID as a path variable
  // and an optional date filter as a query parameter

  @Override
  @GetMapping({"/{id}/lessons"})
  public ResponseEntity<List<Lesson>> listInstructorLessons(
      @PathVariable Integer id, LocalDate date) {
    return ResponseEntity.ok(lessonService.listForInstructor(id, date));
  }

  // Get a list of all instructors

  @Override
  @GetMapping
  public ResponseEntity<List<Instructor>> listInstructors() {
    return ResponseEntity.ok(instructorService.list());
  }

  // Update an instructor by passing their ID as a path variable
  // and an "InstructorUpdate" DTO in the request body
  // public InstructorUpdate(@Nullable LicenseCategory licenseCategory,
  //                        @Nullable Boolean isCompliant,
  //                        @Nullable Boolean isActive)

  @Override
  @PatchMapping("/{id}")
  public ResponseEntity<Instructor> updateInstructor(
      @PathVariable Integer id, InstructorUpdate instructorUpdate) {
    return ResponseEntity.ok(instructorService.update(id, instructorUpdate));
  }
}
