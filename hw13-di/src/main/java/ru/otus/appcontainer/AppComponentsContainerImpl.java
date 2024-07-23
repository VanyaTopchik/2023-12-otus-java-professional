package ru.otus.appcontainer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;

public class AppComponentsContainerImpl implements AppComponentsContainer {

  private final List<Object> appComponents = new ArrayList<>();
  private final Map<String, Object> appComponentsByName = new HashMap<>();

  public AppComponentsContainerImpl(Class<?> initialConfigClass) {
    processConfig(initialConfigClass);
  }

  private void processConfig(Class<?> configClass) {
    checkConfigClass(configClass);
    try {
      initContext(configClass);
    } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
      throw new RuntimeException("Failed initialize context", e);
    }
  }

  private void initContext(Class<?> configClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    var config = configClass.getConstructor().newInstance();
    Arrays.stream(configClass.getDeclaredMethods())
        .filter(m -> m.isAnnotationPresent(AppComponent.class))
        .sorted(Comparator.comparingInt(o -> o.getAnnotation(AppComponent.class).order()))
        .forEachOrdered(method -> putComponentIntoContext(method, config));
  }

  private void putComponentIntoContext(Method method, Object config) {
    String componentName = method.getAnnotation(AppComponent.class).name();
    if (componentName == null) {
      componentName = method.getName();
    }
    if (appComponentsByName.containsKey(componentName)) {
      throw new RuntimeException("Component with name '" + componentName + "' already exists");
    }
    Object[] args = Arrays.stream(method.getParameterTypes()).map(this::getAppComponent).toArray();
    Object component;
    try {
      component = method.invoke(config, args);
    } catch (IllegalAccessException | InvocationTargetException e) {
      throw new RuntimeException("Failed create component", e);
    }
    appComponents.add(component);
    appComponentsByName.put(componentName, component);
  }

  private void checkConfigClass(Class<?> configClass) {
    if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
      throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
    }
  }

  @Override
  public <C> C getAppComponent(Class<C> componentClass) {
    List<Object> list = appComponents.stream().filter(component -> component.getClass().equals(componentClass)
        || Arrays.asList(component.getClass().getInterfaces()).contains((componentClass))).toList();
    if (list.size() > 1) {
      throw new RuntimeException("Founded few components by type");
    } else return (C) list.get(0);
  }

  @Override
  public <C> C getAppComponent(String componentName) {
    return (C) Optional.ofNullable(appComponentsByName.get(componentName)).orElseThrow();
  }
}
