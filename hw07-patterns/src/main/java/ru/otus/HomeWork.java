package ru.otus;

import java.time.LocalDateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.handler.ComplexProcessor;
import ru.otus.listener.HistoryListener;
import ru.otus.model.Message;
import ru.otus.model.ObjectForMessage;
import ru.otus.processor.ProcessorSwapFields11and12;
import ru.otus.processor.ProcessorThrowExceptionTime;

public class HomeWork {
  private static final Logger logger = LoggerFactory.getLogger(HomeWork.class);

  public static void main(String[] args) {
    var processors = List.of(new ProcessorThrowExceptionTime(LocalDateTime::now), new ProcessorSwapFields11and12());

    var complexProcessor = new ComplexProcessor(processors, ex -> logger.info("ex:{}", ex.getMessage()));
    var historyListener = new HistoryListener();
    complexProcessor.addListener(historyListener);

    var field13 = new ObjectForMessage();
    field13.setData(List.of("first", "second"));
    var message = new Message.Builder(1L)
        .field11("field11")
        .field12("field12")
        .field13(field13)
        .build();

    var result = complexProcessor.handle(message);
    logger.info("result:{}", result);

    var msg = historyListener.findMessageById(1L);
    logger.info("msg:{}", msg);

    complexProcessor.removeListener(historyListener);
  }
}
