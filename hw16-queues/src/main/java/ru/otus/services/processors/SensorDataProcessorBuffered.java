package ru.otus.services.processors;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.api.SensorDataProcessor;
import ru.otus.api.model.SensorData;
import ru.otus.lib.SensorDataBufferedWriter;

public class SensorDataProcessorBuffered implements SensorDataProcessor {
  private static final Logger log = LoggerFactory.getLogger(SensorDataProcessorBuffered.class);

  private final int bufferSize;
  private final SensorDataBufferedWriter writer;
  private final BlockingQueue dataBuffer;

  public SensorDataProcessorBuffered(int bufferSize, SensorDataBufferedWriter writer) {
    this.bufferSize = bufferSize;
    this.writer = writer;
    this.dataBuffer = new ArrayBlockingQueue<SensorData>(bufferSize);
  }

  @Override
  public void process(SensorData data) {
    dataBuffer.add(data);
    if (dataBuffer.size() >= bufferSize) {
      flush();
    }
  }

  public void flush() {
    List<SensorData> bufferedData = new ArrayList<>();
    dataBuffer.drainTo(bufferedData, bufferSize);
    if (bufferedData.isEmpty()) {
      return;
    }
    try {
      writer.writeBufferedData(bufferedData.stream().sorted(Comparator.comparing(SensorData::getMeasurementTime)).toList());
    } catch (Exception e) {
      log.error("Ошибка в процессе записи буфера", e);
    }
  }

  @Override
  public void onProcessingEnd() {
    flush();
  }
}
