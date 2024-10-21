package ru.otus.service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.otus.sessionmanager.TransactionManager;
import ru.otus.model.Client;
import ru.otus.repository.ClientRepository;

@Service
public class DbServiceClientImpl implements DBServiceClient {
  private static final Logger log = LoggerFactory.getLogger(DbServiceClientImpl.class);

  private final TransactionManager transactionManager;
  private final ClientRepository repository;

  public DbServiceClientImpl(TransactionManager transactionManager, ClientRepository repository) {
    this.transactionManager = transactionManager;
    this.repository = repository;
  }

  @Override
  public Client saveClient(Client client) {
    return transactionManager.doInTransaction(() -> {
      var savedClient = repository.save(client);
      log.info("saved client: {}", savedClient);
      return savedClient;
    });
  }

  @Override
  public Optional<Client> getClient(long id) {
    return transactionManager.doInTransaction(() -> {
      var clientOptional = repository.findById(id);
      log.info("client: {}", clientOptional);
      return clientOptional;
    });
  }

  @Override
  public List<Client> findAll() {
    return transactionManager.doInTransaction(() -> {
      var clientList = repository.findAll();
      log.info("clientList:{}", clientList);
      return clientList;
    });
  }
}
