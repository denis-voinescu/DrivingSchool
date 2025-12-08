package org.example.drivingschool.controller;

import org.example.drivingschool.service.InstructorService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InstructorController {

    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }
}
