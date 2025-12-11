package org.example.drivingschool.controller;

import org.example.drivingschool.service.LessonService;
import org.openapitools.api.LessonsApi;
import org.openapitools.model.Lesson;
import org.openapitools.model.LessonUpdate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/lessons")
public class LessonController implements LessonsApi {

  private final LessonService lessonService;

  public LessonController(LessonService lessonService) {
    this.lessonService = lessonService;
  }

  // Get a list of all lessons, with an optional date filter as a query parameter

  @Override
  @GetMapping
  public ResponseEntity<List<Lesson>> listLessonsByDate(
      @RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
          LocalDate date) {
    return ResponseEntity.ok(lessonService.list(date));
  }

  // Get a specific lesson by passing its ID as a path variable

  @Override
  @GetMapping("/{id}")
  public ResponseEntity<Lesson> getLessonById(@PathVariable Integer id) {
    return ResponseEntity.ok(lessonService.getById(id));
  }

  // Update a specific lesson by passing its ID as a path variable and
  // a "LessonUpdate" DTO in the request body
  // public LessonUpdate(@Nullable LessonType type,
  //                    @Nullable LocalDate date,
  //                    @Nullable String location,
  //                    @Nullable Boolean completed)

  @Override
  @PatchMapping("/{id}")
  public ResponseEntity<Lesson> updateLesson(@PathVariable Integer id, LessonUpdate lessonUpdate) {
    return ResponseEntity.ok(lessonService.update(id, lessonUpdate));
  }
}
