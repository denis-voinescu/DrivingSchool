package org.example.drivingschool.controller;

import org.example.drivingschool.service.ExamService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExamController {

    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }
}
