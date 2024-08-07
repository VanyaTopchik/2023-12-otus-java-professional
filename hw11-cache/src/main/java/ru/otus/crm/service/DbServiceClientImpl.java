package ru.otus.crm.service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.cachehw.HwCache;
import ru.otus.core.repository.DataTemplate;
import ru.otus.core.sessionmanager.TransactionRunner;
import ru.otus.crm.model.Client;

public class DbServiceClientImpl implements DBServiceClient {
  private static final Logger log = LoggerFactory.getLogger(DbServiceClientImpl.class);

  private final DataTemplate<Client> dataTemplate;
  private final TransactionRunner transactionRunner;
  private final HwCache<String, Client> cache;

  public DbServiceClientImpl(TransactionRunner transactionRunner, DataTemplate<Client> dataTemplate, HwCache<String, Client> cache) {
    this.transactionRunner = transactionRunner;
    this.dataTemplate = dataTemplate;
    this.cache = cache;
  }

  @Override
  public Client saveClient(Client client) {
    Client result = transactionRunner.doInTransaction(connection -> {
      if (client.getId() == null) {
        var clientId = dataTemplate.insert(connection, client);
        var createdClient = new Client(clientId, client.getName());
        log.info("created client: {}", createdClient);
        return createdClient;
      }
      dataTemplate.update(connection, client);
      log.info("updated client: {}", client);
      return client;
    });
    cache.put(result.getId().toString(), result);
    return result;
  }

  @Override
  public Optional<Client> getClient(long id) {
    Client client = cache.get(String.valueOf(id));
    if (client != null) {
      return Optional.of(client);
    }
    return transactionRunner.doInTransaction(connection -> {
      var clientOptional = dataTemplate.findById(connection, id);
      log.info("client: {}", clientOptional);
      clientOptional.ifPresent(value -> cache.put(String.valueOf(id), value));
      return clientOptional;
    });
  }

  @Override
  public List<Client> findAll() {
    return transactionRunner.doInTransaction(connection -> {
      var clientList = dataTemplate.findAll(connection);
      log.info("clientList:{}", clientList);
      return clientList;
    });
  }
}
