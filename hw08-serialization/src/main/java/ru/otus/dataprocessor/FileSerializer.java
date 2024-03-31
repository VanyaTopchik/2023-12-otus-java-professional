package ru.otus.dataprocessor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class FileSerializer implements Serializer {

  private final ObjectMapper mapper;
  private final File file;

  public FileSerializer(String fileName) {
    this.file = new File(fileName);
    this.mapper = JsonMapper.builder().build();
  }

  // формирует результирующий json и сохраняет его в файл
  @Override
  public void serialize(Map<String, Double> data) {
    try {
      mapper.writeValue(file, data);
    } catch (IOException e) {
      throw new FileProcessException(e);
    }
  }
}
