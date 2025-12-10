package org.example.drivingschool.service;

import org.example.drivingschool.exception.InvalidIdException;
import org.example.drivingschool.exception.ResourceNotFoundException;
import org.example.drivingschool.mapper.ExamMapper;
import org.example.drivingschool.model.ExamEntity;
import org.example.drivingschool.repository.ExamRepository;
import org.openapitools.model.Exam;
import org.openapitools.model.ExamCreate;
import org.openapitools.model.ExamUpdate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Service
public class ExamService {

    private final ExamRepository examRepository;
    private final ExamMapper examMapper;

    public ExamService(ExamRepository examRepository, ExamMapper examMapper) {
        this.examRepository = examRepository;
        this.examMapper = examMapper;
    }

    @Transactional(readOnly = true)
    public List<Exam> list(LocalDate date) {
        return examRepository.findAll()
                .stream()
                .map(examMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public Exam getById(Integer id) {
        if (id == null || id <= 0) {
            throw new InvalidIdException();
        }

        ExamEntity entity = examRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        return examMapper.toDto(entity);
    }

    public Exam create(ExamCreate examCreate) {
        ExamEntity entity = examMapper.toEntity(examCreate);
        entity.setCreatedAt(Instant.now());
        ExamEntity saved = examRepository.save(entity);
        return examMapper.toDto(saved);
    }

    public Exam update(Integer id, ExamUpdate patch) {
        if (id == null || id <= 0) {
            throw new InvalidIdException();
        }

        ExamEntity entity = examRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        examMapper.updateEntity(entity, patch);
        entity.setUpdatedAt(Instant.now());

        ExamEntity saved = examRepository.save(entity);
        return examMapper.toDto(saved);
    }
}
