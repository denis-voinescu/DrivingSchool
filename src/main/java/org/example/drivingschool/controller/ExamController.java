package org.example.drivingschool.controller;

import jakarta.validation.Valid;
import org.example.drivingschool.service.ExamService;
import org.openapitools.api.ExamsApi;
import org.openapitools.model.Exam;
import org.openapitools.model.ExamCreate;
import org.openapitools.model.ExamUpdate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/exams")
public class ExamController implements ExamsApi {

    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Exam>> listExamsByDate(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @RequestParam(value = "date", required = false) LocalDate date) {
        return ResponseEntity.ok(examService.list(date));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Exam> getExamById(@PathVariable Integer id) {
        return ResponseEntity.ok(examService.getById(id));
    }


    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<Exam> updateExam(@PathVariable Integer id,
                                           @Valid @RequestBody ExamUpdate examUpdate) {
        return ResponseEntity.ok(examService.update(id, examUpdate));
    }
}
