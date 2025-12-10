package org.example.drivingschool.controller;

import org.example.drivingschool.service.StudentService;
import org.openapitools.api.StudentsApi;
import org.openapitools.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController implements StudentsApi {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Student>> listStudents() {
        return ResponseEntity.ok(studentService.list());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(Integer id) {
        return ResponseEntity.ok(studentService.getById(id));
    }

    @Override
    public ResponseEntity<List<Enrollment>> listStudentEnrollments(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<List<Exam>> listStudentExams(Integer id, LocalDate from, LocalDate to) {
        return null;
    }

    @Override
    public ResponseEntity<List<Lesson>> listStudentLessons(Integer id, Boolean completed) {
        return null;
    }

    @Override
    @PostMapping
    public ResponseEntity<Student> createStudent(StudentCreate studentCreate) {
        return ResponseEntity.ok(studentService.create(studentCreate));
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<Student> updateStudent(Integer id, StudentUpdate studentUpdate) {
        return ResponseEntity.ok(studentService.update(id, studentUpdate));
    }
}
