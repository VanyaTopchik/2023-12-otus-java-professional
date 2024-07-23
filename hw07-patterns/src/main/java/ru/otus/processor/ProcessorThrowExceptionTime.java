package ru.otus.processor;

import java.time.LocalDateTime;
import ru.otus.model.Message;

public class ProcessorThrowExceptionTime implements Processor {

  private final LocalDateTimeProvider localDateTimeProvider;

  public ProcessorThrowExceptionTime(LocalDateTimeProvider localDateTimeProvider) {
    this.localDateTimeProvider = localDateTimeProvider;
  }

  @Override
  public Message process(Message message) {
    int seconds = localDateTimeProvider.getSeconds();
    if (seconds % 2 == 0) {
      throw new RuntimeException("even second:" + seconds);
    }
    return message;
  }
}
