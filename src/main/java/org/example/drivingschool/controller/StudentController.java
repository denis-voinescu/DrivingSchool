package org.example.drivingschool.controller;

import org.example.drivingschool.service.EnrollmentService;
import org.example.drivingschool.service.LessonService;
import org.example.drivingschool.service.StudentService;
import org.openapitools.api.StudentsApi;
import org.openapitools.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController implements StudentsApi {

    private final StudentService studentService;
    private final EnrollmentService enrollmentService;
    private final LessonService lessonService;

    public StudentController(LessonService lessonService, StudentService studentService, EnrollmentService enrollmentService) {
        this.studentService = studentService;
        this.enrollmentService = enrollmentService;
        this.lessonService = lessonService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Student>> listStudents() {
        return ResponseEntity.ok(studentService.list());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer id) {
        return ResponseEntity.ok(studentService.getById(id));
    }

    @Override
    @GetMapping("/{id}/enrollments")
    public ResponseEntity<List<Enrollment>> listStudentEnrollments(@PathVariable Integer id) {
        return ResponseEntity.ok(enrollmentService.listForStudent(id));
    }

    @Override
    @GetMapping("/{id}/lessons")
    public ResponseEntity<List<Lesson>> listStudentLessons(
            @PathVariable Integer id,
            @RequestParam(value = "completed", required = false) Boolean completed) {

        return ResponseEntity.ok(lessonService.listForStudent(id, completed));
    }


    @Override
    @PostMapping
    public ResponseEntity<Student> createStudent(StudentCreate studentCreate) {
        return ResponseEntity.ok(studentService.create(studentCreate));
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Integer id, StudentUpdate studentUpdate) {
        return ResponseEntity.ok(studentService.update(id, studentUpdate));
    }
}
