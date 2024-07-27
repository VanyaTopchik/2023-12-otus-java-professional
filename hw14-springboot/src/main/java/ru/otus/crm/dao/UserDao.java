package ru.otus.crm.dao;

import java.util.Optional;
import ru.otus.crm.model.User;

public interface UserDao {

  Optional<User> findByLogin(String login);
}
