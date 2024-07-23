package ru.otus.processor;

import java.time.LocalDateTime;

public interface LocalDateTimeProvider {
  LocalDateTime getDate();

  default int getSeconds() {
    return getDate().getSecond();
  }
}
