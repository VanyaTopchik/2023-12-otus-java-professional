package ru.otus.dataprocessor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import ru.otus.model.Measurement;

public class ResourcesFileLoader implements Loader {

  private final ObjectMapper mapper;
  private final File file;

  public ResourcesFileLoader(String fileName) {
    URL resource = getClass().getClassLoader().getResource(fileName);
    if (resource == null) {
      throw new FileProcessException("file not found!");
    } else {
      try {
        this.file = new File(resource.toURI());
      } catch (URISyntaxException e) {
        throw new FileProcessException(e);
      }
    }
    this.mapper = JsonMapper.builder().build();
  }

  // читает файл, парсит и возвращает результат
  @Override
  public List<Measurement> load() {
    Measurement[] measurements;
    try {
      measurements = mapper.readValue(file, Measurement[].class);
    } catch (IOException e) {
      throw new FileProcessException(e);
    }
    return Arrays.asList(measurements);
  }
}
