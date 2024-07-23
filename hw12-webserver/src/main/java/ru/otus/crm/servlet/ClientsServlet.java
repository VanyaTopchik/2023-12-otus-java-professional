package ru.otus.crm.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import ru.otus.core.service.TemplateProcessor;
import ru.otus.crm.model.Address;
import ru.otus.crm.model.Client;
import ru.otus.crm.model.Phone;
import ru.otus.crm.service.DbServiceClientImpl;

public class ClientsServlet extends HttpServlet {

  private static final String CLIENTS_PAGE_TEMPLATE = "clients.html";
  private static final String TEMPLATE_ATTR_ALL_CLIENTS = "allClients";
  private static final String PARAM_CLIENT_NAME = "name";
  private static final String PARAM_CLIENT_STREET = "street";
  private static final String PARAM_CLIENT_PHONE_NUMBERS = "phone_numbers";

  private final DbServiceClientImpl serviceClient;
  private final TemplateProcessor templateProcessor;

  public ClientsServlet(TemplateProcessor templateProcessor, DbServiceClientImpl serviceClient) {
    this.templateProcessor = templateProcessor;
    this.serviceClient = serviceClient;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
    getClientsPage(response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Client client = extractClientFromRequest(request);
    serviceClient.saveClient(client);
    getClientsPage(response);
  }

  private void getClientsPage(HttpServletResponse response) throws IOException {
    Map<String, Object> paramsMap = new HashMap<>();
    List<Client> clients = serviceClient.findAll();
    paramsMap.put(TEMPLATE_ATTR_ALL_CLIENTS, clients);

    response.setContentType("text/html");
    response.getWriter().println(templateProcessor.getPage(CLIENTS_PAGE_TEMPLATE, paramsMap));
  }

  private Client extractClientFromRequest(HttpServletRequest request) {
    String name = request.getParameter(PARAM_CLIENT_NAME);
    String street = request.getParameter(PARAM_CLIENT_STREET);
    String phoneNumbers = request.getParameter(PARAM_CLIENT_PHONE_NUMBERS);

    var client = new Client(name);

    if (StringUtils.isNotBlank(street)) {
      client.setAddress(new Address(null, street));
    }

    List<Phone> phones = new ArrayList<>();
    if (StringUtils.isNotBlank(phoneNumbers)) {
      Arrays.stream(phoneNumbers.split(",")).forEach(number -> phones.add(new Phone(null, number)));
      client.setPhones(phones);
    }

    return client;
  }
}
