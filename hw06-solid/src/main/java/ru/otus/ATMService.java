package ru.otus;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ATMService {
  private static final Logger logger = LoggerFactory.getLogger(ATMService.class);

  private final BanknoteStorage storage;

  public ATMService() {
    this.storage = new BanknoteStorage();
  }

  public void acceptBanknotes(List<Banknote> banknotes) {
    if (banknotes.isEmpty()) {
      return;
    }
    for (var banknote : banknotes) {
      storage.acceptBanknote(banknote);
      logger.info("Added banknote:{}", banknote.getNominal());
    }
  }

  public Map<Banknote, Integer> getCash(int sum) {
    if (sum <= 0) {
      throw new RuntimeException("sum must be greater than zero");
    }
    Map<Banknote, Integer> banknotes = storage.getCash(sum);
    logger.info("Getting banknotes:{}", banknotes);
    return banknotes;
  }

  public String getStorageInfo() {
    return storage.getInfo();
  }
}
