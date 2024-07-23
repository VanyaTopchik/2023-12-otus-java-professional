package ru.otus.crm.server;

import com.google.gson.Gson;
import java.util.Arrays;
import org.eclipse.jetty.ee10.servlet.FilterHolder;
import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.ee10.servlet.ServletHolder;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ResourceHandler;
import ru.otus.core.helpers.FileSystemHelper;
import ru.otus.core.service.TemplateProcessor;
import ru.otus.crm.service.DbServiceClientImpl;
import ru.otus.crm.service.UserAuthService;
import ru.otus.crm.servlet.AuthorizationFilter;
import ru.otus.crm.servlet.ClientsServlet;
import ru.otus.crm.servlet.LoginServlet;

public class ClientsWebServerImpl implements ClientsWebServer {
  private static final String START_PAGE_NAME = "index.html";
  private static final String COMMON_RESOURCES_DIR = "static";

  private final DbServiceClientImpl serviceClient;
  private final Gson gson;
  protected final TemplateProcessor templateProcessor;
  private final Server server;
  private final UserAuthService authService;

  public ClientsWebServerImpl(int port, UserAuthService authService, DbServiceClientImpl serviceClient, Gson gson, TemplateProcessor templateProcessor) {
    this.serviceClient = serviceClient;
    this.gson = gson;
    this.templateProcessor = templateProcessor;
    this.authService = authService;
    server = new Server(port);
  }

  @Override
  public void start() throws Exception {
    if (server.getHandlers().isEmpty()) {
      initContext();
    }
    server.start();
  }

  @Override
  public void join() throws Exception {
    server.join();
  }

  @Override
  public void stop() throws Exception {
    server.stop();
  }

  private void initContext() {
    ResourceHandler resourceHandler = createResourceHandler();
    ServletContextHandler servletContextHandler = createServletContextHandler();

    Handler.Sequence sequence = new Handler.Sequence();
    sequence.addHandler(resourceHandler);
    sequence.addHandler(applySecurity(servletContextHandler, "/clients"));

    server.setHandler(sequence);
  }

  @Override
  public Handler applySecurity(ServletContextHandler servletContextHandler, String... paths) {
    servletContextHandler.addServlet(new ServletHolder(new LoginServlet(templateProcessor, authService)), "/login");
    AuthorizationFilter authorizationFilter = new AuthorizationFilter();
    Arrays.stream(paths)
        .forEachOrdered(
            path -> servletContextHandler.addFilter(new FilterHolder(authorizationFilter), path, null));
    return servletContextHandler;
  }

  private ResourceHandler createResourceHandler() {
    ResourceHandler resourceHandler = new ResourceHandler();
    resourceHandler.setDirAllowed(false);
    resourceHandler.setWelcomeFiles(START_PAGE_NAME);
    resourceHandler.setBaseResourceAsString(
        FileSystemHelper.localFileNameOrResourceNameToFullPath(COMMON_RESOURCES_DIR));
    return resourceHandler;
  }

  private ServletContextHandler createServletContextHandler() {
    ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
    servletContextHandler.addServlet(new ServletHolder(new ClientsServlet(templateProcessor, serviceClient)), "/clients");
    return servletContextHandler;
  }
}
