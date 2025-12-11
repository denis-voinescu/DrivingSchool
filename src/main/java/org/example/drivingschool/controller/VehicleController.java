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

  // Get a list of all vehicles

  @Override
  @GetMapping
  public ResponseEntity<List<Vehicle>> listVehicles() {
    return ResponseEntity.ok(vehicleService.list());
  }

  // Get a specific vehicle by passing its ID as a path variable

  @Override
  @GetMapping("/{id}")
  public ResponseEntity<Vehicle> getVehicleById(@PathVariable Integer id) {
    return ResponseEntity.ok(vehicleService.getById(id));
  }

  // Create a vehicle by passing a "VehicleCreate" DTO in the request body
  //  public VehicleCreate(
  //          String registrationNumber,
  //          LicenseCategory licenseCategory,
  //          Integer minAge,
  //          String make,
  //          String model,
  //          Integer year,
  //          String transmission,
  //          String fuelType)

  @Override
  @PostMapping
  public ResponseEntity<Vehicle> createVehicle(@Valid @RequestBody VehicleCreate vehicleCreate) {
    return ResponseEntity.ok(vehicleService.create(vehicleCreate));
  }

  // Update a specific vehicle by passing its ID as a path variable and
  // a "VehicleUpdate" DTO in the request body
  //    public VehicleUpdate(
  //            @Nullable LicenseCategory licenseCategory,
  //            @Nullable Integer minAge,
  //            @Nullable String make,
  //            @Nullable String model,
  //            @Nullable Integer year,
  //            @Nullable String transmission,
  //            @Nullable String fuelType,
  //            @Nullable Boolean isActive

  @Override
  @PatchMapping("/{id}")
  public ResponseEntity<Vehicle> updateVehicle(
      @PathVariable Integer id, @RequestBody VehicleUpdate vehicleUpdate) {
    return ResponseEntity.ok(vehicleService.update(id, vehicleUpdate));
  }
}
