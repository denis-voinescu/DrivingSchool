package org.example.drivingschool.service;

import org.example.drivingschool.exception.InvalidIdException;
import org.example.drivingschool.exception.ResourceNotFoundException;
import org.example.drivingschool.mapper.EnrollmentMapper;
import org.example.drivingschool.model.EnrollmentEntity;
import org.example.drivingschool.model.InstructorEntity;
import org.example.drivingschool.model.StudentEntity;
import org.example.drivingschool.model.VehicleEntity;
import org.example.drivingschool.repository.EnrollmentRepository;
import org.example.drivingschool.repository.InstructorRepository;
import org.example.drivingschool.repository.StudentRepository;
import org.example.drivingschool.repository.VehicleRepository;
import org.openapitools.model.Enrollment;
import org.openapitools.model.EnrollmentCreate;
import org.openapitools.model.EnrollmentUpdate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final InstructorRepository instructorRepository;
    private final VehicleRepository vehicleRepository;
    private final EnrollmentMapper enrollmentMapper;

    public EnrollmentService(EnrollmentRepository enrollmentRepository,
                             StudentRepository studentRepository,
                             InstructorRepository instructorRepository,
                             VehicleRepository vehicleRepository,
                             EnrollmentMapper enrollmentMapper) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.instructorRepository = instructorRepository;
        this.vehicleRepository = vehicleRepository;
        this.enrollmentMapper = enrollmentMapper;
    }

    @Transactional(readOnly = true)
    public List<Enrollment> list() {
        return enrollmentRepository.findAll()
                .stream()
                .map(enrollmentMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public Enrollment getById(Integer id) {
        if (id == null || id <= 0) {
            throw new InvalidIdException();
        }

        EnrollmentEntity entity = enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        return enrollmentMapper.toDto(entity);
    }

    public Enrollment create(EnrollmentCreate enrollmentCreate) {

        StudentEntity student = studentRepository.findById(enrollmentCreate.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException(enrollmentCreate.getStudentId()));

        InstructorEntity instructor = instructorRepository.findById(enrollmentCreate.getInstructorId())
                .orElseThrow(() -> new ResourceNotFoundException(enrollmentCreate.getInstructorId()));

        VehicleEntity vehicle = vehicleRepository.findById(enrollmentCreate.getVehicleId())
                .orElseThrow(() -> new ResourceNotFoundException(enrollmentCreate.getVehicleId()));

        EnrollmentEntity entity = enrollmentMapper.toEntity(enrollmentCreate);
        entity.setStudent(student);
        entity.setInstructor(instructor);
        entity.setVehicle(vehicle);
        entity.setCreatedAt(Instant.now());

        EnrollmentEntity saved = enrollmentRepository.save(entity);
        return enrollmentMapper.toDto(saved);
    }

    public Enrollment update(Integer id, EnrollmentUpdate patch) {
        if (id == null || id <= 0) {
            throw new InvalidIdException();
        }

        EnrollmentEntity entity = enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        if (patch.getInstructorId() != null) {
            InstructorEntity instructor = instructorRepository.findById(patch.getInstructorId())
                    .orElseThrow(() -> new ResourceNotFoundException(patch.getInstructorId()));
            entity.setInstructor(instructor);
        }

        if (patch.getVehicleId() != null) {
            VehicleEntity vehicle = vehicleRepository.findById(patch.getVehicleId())
                    .orElseThrow(() -> new ResourceNotFoundException(patch.getVehicleId()));
            entity.setVehicle(vehicle);
        }

        entity.setUpdatedAt(Instant.now());

        EnrollmentEntity saved = enrollmentRepository.save(entity);
        return enrollmentMapper.toDto(saved);
    }
}
