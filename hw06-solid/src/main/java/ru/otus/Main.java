package ru.otus;

import java.util.ArrayList;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
  private static final Logger logger = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) {
    ATMService atmService = new ATMService();
    Random random = new Random();
    ArrayList<Banknote> banknotes = new ArrayList<>();
    for (var banknote : Banknote.values()) {
      int count = random.nextInt(0, 10);
      for (int i = 0; i < count; i++) {
        banknotes.add(banknote);
      }
    }
    atmService.acceptBanknotes(banknotes);
    logger.info("Info before getting cash:\n{}", atmService.getStorageInfo());
    atmService.getCash(12500);
    logger.info("Info after getting cash:\n{}", atmService.getStorageInfo());
  }
}