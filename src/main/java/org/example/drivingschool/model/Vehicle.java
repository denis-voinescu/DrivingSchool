package org.example.drivingschool.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "registration_number", nullable = false, length = Integer.MAX_VALUE)
    private String registrationNumber;

    @NotNull
    @Column(name = "min_age", nullable = false)
    private Integer minAge;

    @NotNull
    @Column(name = "make", nullable = false, length = Integer.MAX_VALUE)
    private String make;

    @NotNull
    @Column(name = "model", nullable = false, length = Integer.MAX_VALUE)
    private String model;

    @NotNull
    @Column(name = "year", nullable = false)
    private Integer year;

    @NotNull
    @Column(name = "transmission", nullable = false, length = Integer.MAX_VALUE)
    private String transmission;

    @NotNull
    @Column(name = "fuel_type", nullable = false, length = Integer.MAX_VALUE)
    private String fuelType;

    @NotNull
    @ColumnDefault("true")
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = false;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;
    @Column(name = "updated_at")
    private Instant updatedAt;

/*
 TODO [Reverse Engineering] create field to map the 'license_category' column
 Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @Column(name = "license_category", columnDefinition = "license_category not null")
    private Object licenseCategory;
*/
}