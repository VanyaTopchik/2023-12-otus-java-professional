package ru.otus;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class AOPLogging {
  private static final Logger logger = LoggerFactory.getLogger(AOPLogging.class);
  private static Set<Method> annotatedMethods;

  private AOPLogging() {
  }

  static Object createProxyLogging(Class<?> clazz) throws Exception {
    InvocationHandler handler = new ProxyInvocationHandler(clazz.getDeclaredConstructor().newInstance());
    annotatedMethods = Arrays.stream(clazz.getMethods())
        .filter(method -> method.isAnnotationPresent(Log.class))
        .collect(Collectors.toSet());
    return Proxy.newProxyInstance(AOPLogging.class.getClassLoader(), clazz.getInterfaces(), handler);
  }

  static class ProxyInvocationHandler implements InvocationHandler {
    private final Object object;

    ProxyInvocationHandler(Object object) {
      this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      if (annotatedMethods.stream().anyMatch(m -> equalsMethodSignatures(m, method))) {
        logger.info("executed method:{}, param:{}", method.getName(),
            args != null
                ? Arrays.stream(args)
                .map(Object::toString)
                .collect(Collectors.joining(","))
                : "");
      }
      return method.invoke(object, args);
    }

    @Override
    public String toString() {
      return "ProxyInvocationHandler{" +
          "object=" + object +
          '}';
    }

    private boolean equalsMethodSignatures(Method m1, Method m2) {
      if (!m1.getName().equals(m2.getName())) {
        return false;
      }
      if (!Arrays.equals(m1.getParameterTypes(), m2.getParameterTypes())) {
        return false;
      }
      return m1.getReturnType().equals(m2.getReturnType());
    }
  }
}
