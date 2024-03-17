package ru.otus;

import java.util.EnumMap;
import java.util.Map;

public class BanknoteStorage {
  private final Map<Banknote, Integer> map;

  public BanknoteStorage() {
    this.map = new EnumMap<>(Banknote.class);
  }

  public void acceptBanknote(Banknote banknote) {
    map.merge(banknote, 1, Integer::sum);
  }

  public Map<Banknote, Integer> getCash(int sum) {
    int leftSum = sum;
    EnumMap<Banknote, Integer> banknotes = new EnumMap<>(Banknote.class);
    for (var entry : map.entrySet()) {
      Banknote banknote = entry.getKey();
      Integer count = entry.getValue();
      while (leftSum >= banknote.getNominal() && count > 0) {
        banknotes.merge(banknote, 1, Integer::sum);
        count = map.merge(banknote, -1, Integer::sum);
        leftSum -= banknote.getNominal();
      }
    }
    if (leftSum != 0) {
      throw new RuntimeException("There are no banknotes for getting cash");
    }
    return banknotes;
  }

  public String getInfo() {
    StringBuilder builder = new StringBuilder();
    builder.append("ATM contains:");
    for (Map.Entry<Banknote, Integer> pair : map.entrySet()) {
      builder.append(String.format("\n%d banknotes with nominal '%s'", pair.getValue(), pair.getKey().getNominal()));
    }
    builder.append("\nTotal sum: ").append(getTotalSum());
    return builder.toString();
  }

  private int getTotalSum() {
    int totalSum = 0;
    for (Map.Entry<Banknote, Integer> pair : map.entrySet()) {
      totalSum += pair.getKey().getNominal() * pair.getValue();
    }
    return totalSum;
  }
}
