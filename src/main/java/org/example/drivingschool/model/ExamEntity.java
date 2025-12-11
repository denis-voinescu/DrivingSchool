package org.example.drivingschool.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "exam")
public class ExamEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "enrollment_id", nullable = false)
  private EnrollmentEntity enrollment;

  @NotNull
  @Column(name = "date", nullable = false)
  private LocalDate date;

  @NotNull
  @Column(name = "hour", nullable = false)
  private LocalTime hour;

  @NotNull
  @Column(name = "location", nullable = false, length = Integer.MAX_VALUE)
  private String location;

  @NotNull
  @ColumnDefault("false")
  @Column(name = "completed", nullable = false)
  private Boolean completed = false;

  @NotNull
  @Column(name = "created_at", nullable = false)
  private Instant createdAt;

  @Column(name = "updated_at")
  private Instant updatedAt;
}
