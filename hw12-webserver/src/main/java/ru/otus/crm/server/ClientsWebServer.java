package ru.otus.crm.server;

import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.server.Handler;

@SuppressWarnings({"squid:S112"})
public interface ClientsWebServer {
  void start() throws Exception;

  void join() throws Exception;

  void stop() throws Exception;

  Handler applySecurity(ServletContextHandler servletContextHandler, String... paths);
}
