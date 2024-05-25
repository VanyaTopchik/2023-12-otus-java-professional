package ru.otus.jdbc.exception;

public class EntityMapperException extends RuntimeException {
  public EntityMapperException(String message) {
    super(message);
  }

  public EntityMapperException(String message, Exception ex) {
    super(message, ex);
  }
}
