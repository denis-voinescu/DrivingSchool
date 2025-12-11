package org.example.drivingschool.controller;

import org.example.drivingschool.service.EnrollmentService;
import org.example.drivingschool.service.ExamService;
import org.example.drivingschool.service.LessonService;
import org.openapitools.api.EnrollmentsApi;
import org.openapitools.model.Enrollment;
import org.openapitools.model.EnrollmentCreate;
import org.openapitools.model.EnrollmentUpdate;
import org.openapitools.model.Exam;
import org.openapitools.model.ExamCreate;
import org.openapitools.model.Lesson;
import org.openapitools.model.LessonCreate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/enrollments")
public class EnrollmentController implements EnrollmentsApi {

  private final EnrollmentService enrollmentService;
  private final ExamService examService;
  private final LessonService lessonService;

  public EnrollmentController(
      EnrollmentService enrollmentService, ExamService examService, LessonService lessonService) {
    this.enrollmentService = enrollmentService;
    this.examService = examService;
    this.lessonService = lessonService;
  }

  // Get a list of all enrollments

  @Override
  @GetMapping
  public ResponseEntity<List<Enrollment>> listEnrollments() {
    return ResponseEntity.ok(enrollmentService.list());
  }

  // Get enrollment by ID

  @Override
  @GetMapping("/{id}")
  public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable Integer id) {
    return ResponseEntity.ok(enrollmentService.getById(id));
  }

  // Get the list of exams scheduled for a specific enrollment

  @Override
  @GetMapping("/{id}/exams")
  public ResponseEntity<List<Exam>> listEnrollmentExams(@PathVariable Integer id) {
    return ResponseEntity.ok(examService.listByEnrollment(id));
  }

  // Create an enrollment by passing an "EnrollmentCreate" DTO in the request body
  // EnrollmentCreate(Integer studentId, Integer instructorId, Integer vehicleId)

  @Override
  @PostMapping
  public ResponseEntity<Enrollment> createEnrollment(
      @RequestBody EnrollmentCreate enrollmentCreate) {
    return ResponseEntity.ok(enrollmentService.create(enrollmentCreate));
  }

  // Create an exam for a specific enrollment
  // by passing an enrollment ID in the request path and
  // an "ExamCreate" DTO in the request body
  // ExamCreate(LocalDate date, String hour, String location)

  @Override
  @PostMapping("/{id}/exams")
  public ResponseEntity<Exam> createExamForEnrollment(
      @PathVariable Integer id, @RequestBody ExamCreate examCreate) {
    return ResponseEntity.ok(examService.createForEnrollment(id, examCreate));
  }

  // Create a lesson for a specific enrollment
  // by passing an enrollment ID in the request path and
  // a "LessonCreate" DTO in the request body
  // LessonCreate(LessonType type, LocalDate date, String location)

  @Override
  @PostMapping("/{id}/lessons")
  public ResponseEntity<Lesson> createLessonForEnrollment(
      @PathVariable Integer id, @RequestBody LessonCreate lessonCreate) {
    return ResponseEntity.ok(lessonService.createForEnrollment(id, lessonCreate));
  }

  // Update a specific enrollment
  // by passing an enrollment ID in the request path and
  // an "EnrollmentUpdate" DTO in the request body
  // public EnrollmentUpdate(@Nullable Integer instructorId,
  //                        @Nullable Integer vehicleId)

  @Override
  @PatchMapping("/{id}")
  public ResponseEntity<Enrollment> updateEnrollment(
      @PathVariable Integer id, @RequestBody EnrollmentUpdate enrollmentUpdate) {
    return ResponseEntity.ok(enrollmentService.update(id, enrollmentUpdate));
  }
}
