package ru.otus.demo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import org.hibernate.cfg.Configuration;
import ru.otus.core.repository.DataTemplateHibernate;
import ru.otus.core.repository.HibernateUtils;
import ru.otus.core.service.TemplateProcessor;
import ru.otus.core.service.TemplateProcessorImpl;
import ru.otus.core.sessionmanager.TransactionManagerHibernate;
import ru.otus.crm.dao.InMemoryUserDao;
import ru.otus.crm.dao.UserDao;
import ru.otus.crm.dbmigrations.MigrationsExecutorFlyway;
import ru.otus.crm.model.Address;
import ru.otus.crm.model.Client;
import ru.otus.crm.model.Phone;
import ru.otus.crm.server.ClientsWebServer;
import ru.otus.crm.server.ClientsWebServerImpl;
import ru.otus.crm.service.DbServiceClientImpl;
import ru.otus.crm.service.UserAuthService;
import ru.otus.crm.service.UserAuthServiceImpl;

public class WebServerDemo {

  private static final int WEB_SERVER_PORT = 8080;
  private static final String TEMPLATES_DIR = "/templates/";
  public static final String HIBERNATE_CFG_FILE = "hibernate.cfg.xml";
  private static final String REALM_NAME = "AnyRealm";

  public static void main(String[] args) throws Exception {
    var configuration = new Configuration().configure(HIBERNATE_CFG_FILE);

    var dbUrl = configuration.getProperty("hibernate.connection.url");
    var dbUserName = configuration.getProperty("hibernate.connection.username");
    var dbPassword = configuration.getProperty("hibernate.connection.password");

    new MigrationsExecutorFlyway(dbUrl, dbUserName, dbPassword).executeMigrations();
    var sessionFactory = HibernateUtils.buildSessionFactory(configuration, Address.class, Client.class, Phone.class);
    var transactionManager = new TransactionManagerHibernate(sessionFactory);
    var clientTemplate = new DataTemplateHibernate<>(Client.class);
    var dbServiceClient = new DbServiceClientImpl(transactionManager, clientTemplate);

    dbServiceClient.saveClient(new Client(null, "FirstClient", new Address(null, "Москва, ул.Гагарина"), null));
    dbServiceClient.saveClient(new Client(null, "SecondClient", new Address(null, "Москва, ул.Маяковская"), null));
    dbServiceClient.saveClient(new Client(null, "ThirdClient"));
    dbServiceClient.saveClient(new Client(null, "FourthClient", null, List.of(new Phone(null, "+7(900)1111111"), new Phone(null, "+7(900)2222222"))));
    dbServiceClient.saveClient(new Client(null, "FifthClient", new Address(null, "Москва, Ленинский проспект"), List.of(new Phone(null, "+7(900)3333333"))));

    UserDao userDao = new InMemoryUserDao();
    Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
    TemplateProcessor templateProcessor = new TemplateProcessorImpl(TEMPLATES_DIR);
    UserAuthService authService = new UserAuthServiceImpl(userDao);

    ClientsWebServer clientsWebServer = new ClientsWebServerImpl(WEB_SERVER_PORT, authService, dbServiceClient, gson, templateProcessor);

    clientsWebServer.start();
    clientsWebServer.join();
  }
}
