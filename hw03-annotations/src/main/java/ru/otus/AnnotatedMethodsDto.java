package ru.otus;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public record AnnotatedMethodsDto(List<Method> beforeMethods,
                                  List<Method> afterMethods,
                                  List<Method> testedMethods) {
  public AnnotatedMethodsDto() {
    this(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
  }
}
