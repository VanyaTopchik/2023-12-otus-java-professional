package ru.otus.dataprocessor;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import ru.otus.model.Measurement;

public class ProcessorAggregator implements Processor {

  // группирует выходящий список по name, при этом суммирует поля value
  @Override
  public Map<String, Double> process(List<Measurement> data) {
    Map<String, Double> map = new TreeMap<>();
    for (Measurement measurement : data) {
      map.merge(measurement.name(), measurement.value(), Double::sum);
    }
    return map;
  }
}
