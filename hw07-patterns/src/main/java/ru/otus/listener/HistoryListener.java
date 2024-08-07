package ru.otus.listener;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import ru.otus.model.Message;

public class HistoryListener implements Listener, HistoryReader {

  private final Map<Long, Message> history = new HashMap<>();

  @Override
  public void onUpdated(Message msg) {
    history.put(msg.getId(), msg.copy());
  }

  @Override
  public Optional<Message> findMessageById(long id) {
    return Optional.of(history.get(id));
  }
}
