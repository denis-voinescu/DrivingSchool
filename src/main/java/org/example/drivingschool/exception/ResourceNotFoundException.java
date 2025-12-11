package org.example.drivingschool.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {
  private final Integer id;

  public ResourceNotFoundException(Integer id) {
    super("Could not find resource with ID: " + id);
    this.id = id;
  }
}
