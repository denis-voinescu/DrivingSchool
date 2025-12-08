package org.example.drivingschool.controller;

import org.example.drivingschool.service.VehicleService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }
}
