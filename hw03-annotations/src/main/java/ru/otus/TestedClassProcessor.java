package ru.otus;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestedClassProcessor {
  private static final Logger LOGGER = LoggerFactory.getLogger(TestedClassProcessor.class);

  private TestedClassProcessor() {
  }

  public static void startTesting(Class<TestedClass> testedClass) {
    int tests = 0;
    int passed = 0;
    int failed = 0;
    AnnotatedMethodsDto dto = new AnnotatedMethodsDto();
    fillDtoByTestedClass(testedClass, dto);
    for (Method m : dto.testedMethods()) {
      tests++;
      try {
        Object object = instantiate(testedClass);
        callMethods(object, dto.beforeMethods());
        callMethod(object, m.getName());
        callMethods(object, dto.afterMethods());
        passed++;
      } catch (Exception e) {
        failed++;
        LOGGER.error("Test '{}' failed. Exception :{}", m.getName(), e);
      }
      m.setAccessible(false);
    }
    LOGGER.info("Tests\u001B[31m failed:{}\u001B[0m, \u001B[32mpassed:{}\u001B[0m of {} tests", failed, passed, tests);
  }

  private static void fillDtoByTestedClass(Class<TestedClass> testedClass, AnnotatedMethodsDto dto) {
    for (Method m : testedClass.getDeclaredMethods()) {
      if (m.isAnnotationPresent(Before.class)) {
        dto.beforeMethods().add(m);
      }
      if (m.isAnnotationPresent(After.class)) {
        dto.afterMethods().add(m);
      }
      if (m.isAnnotationPresent(Test.class)) {
        dto.testedMethods().add(m);
      }
    }
  }

  private static void callMethods(Object object, List<Method> methods) {
    for (Method method : methods) {
      callMethod(object, method.getName());
      method.setAccessible(false);
    }
  }

  private static Object callMethod(Object object, String name, Object... args) {
    try {
      var method = object.getClass().getDeclaredMethod(name, toClasses(args));
      method.setAccessible(true);
      return method.invoke(object, args);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private static <T> T instantiate(Class<T> type, Object... args) {
    try {
      if (args.length == 0) {
        return type.getDeclaredConstructor().newInstance();
      } else {
        Class<?>[] classes = toClasses(args);
        return type.getDeclaredConstructor(classes).newInstance(args);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private static Class<?>[] toClasses(Object[] args) {
    return Arrays.stream(args).map(Object::getClass).toArray(Class<?>[]::new);
  }

}
