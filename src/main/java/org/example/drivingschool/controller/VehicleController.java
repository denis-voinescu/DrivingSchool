package org.example.drivingschool.controller;

import jakarta.validation.Valid;
import org.example.drivingschool.service.VehicleService;
import org.openapitools.api.VehiclesApi;
import org.openapitools.model.Vehicle;
import org.openapitools.model.VehicleCreate;
import org.openapitools.model.VehicleUpdate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/vehicles")
public class VehicleController implements VehiclesApi {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Vehicle>> listVehicles() {
        return ResponseEntity.ok(vehicleService.list());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Integer id) {
        return ResponseEntity.ok(vehicleService.getById(id));
    }

    @Override
    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@Valid @RequestBody VehicleCreate vehicleCreate) {
        return ResponseEntity.ok(vehicleService.create(vehicleCreate));
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Integer id, @RequestBody VehicleUpdate vehicleUpdate) {
        return ResponseEntity.ok(vehicleService.update(id, vehicleUpdate));
    }
}
