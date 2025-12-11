package org.example.drivingschool.service;

import org.example.drivingschool.exception.InvalidIdException;
import org.example.drivingschool.exception.ResourceNotFoundException;
import org.example.drivingschool.mapper.VehicleMapper;
import org.example.drivingschool.model.VehicleEntity;
import org.example.drivingschool.repository.VehicleRepository;
import org.openapitools.model.Vehicle;
import org.openapitools.model.VehicleCreate;
import org.openapitools.model.VehicleUpdate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class VehicleService {

  private final VehicleRepository vehicleRepository;
  private final VehicleMapper vehicleMapper;

  public VehicleService(VehicleRepository vehicleRepository, VehicleMapper vehicleMapper) {
    this.vehicleRepository = vehicleRepository;
    this.vehicleMapper = vehicleMapper;
  }

  @Transactional(readOnly = true)
  public List<Vehicle> list() {
    return vehicleRepository.findAll().stream().map(vehicleMapper::toDto).toList();
  }

  @Transactional(readOnly = true)
  public Vehicle getById(Integer id) {
    if (id == null || id <= 0) {
      throw new InvalidIdException();
    }

    VehicleEntity entity =
        vehicleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

    return vehicleMapper.toDto(entity);
  }

  public Vehicle create(VehicleCreate vehicleCreate) {
    VehicleEntity entity = vehicleMapper.toEntity(vehicleCreate);
    entity.setCreatedAt(Instant.now());
    VehicleEntity saved = vehicleRepository.save(entity);
    return vehicleMapper.toDto(saved);
  }

  public Vehicle update(Integer id, VehicleUpdate patch) {
    if (id == null || id <= 0) {
      throw new InvalidIdException();
    }

    VehicleEntity entity =
        vehicleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

    vehicleMapper.updateEntity(entity, patch);
    entity.setUpdatedAt(Instant.now());

    VehicleEntity saved = vehicleRepository.save(entity);
    return vehicleMapper.toDto(saved);
  }
}
