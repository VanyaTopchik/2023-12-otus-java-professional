package ru.otus.appcontainer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;

@SuppressWarnings("squid:S1068")
public class AppComponentsContainerImpl implements AppComponentsContainer {

  private final List<Object> appComponents = new ArrayList<>();
  private final Map<String, Object> appComponentsByName = new HashMap<>();

  public AppComponentsContainerImpl(Class<?> initialConfigClass) {
    processConfig(initialConfigClass);
  }

  private void processConfig(Class<?> configClass) {
    checkConfigClass(configClass);
    // You code here...
  }

  private void checkConfigClass(Class<?> configClass) {
    if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
      throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
    }
  }

  @Override
  public <C> C getAppComponent(Class<C> componentClass) {
    return null;
  }

  @Override
  public <C> C getAppComponent(String componentName) {
    return null;
  }
}
