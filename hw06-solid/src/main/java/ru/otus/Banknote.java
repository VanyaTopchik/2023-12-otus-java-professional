package ru.otus;

public enum Banknote {
  FIVE_THOUSAND(5000),
  THOUSAND(1000),
  FIVE_HUNDRED(500),
  ONE_HUNDRED(100),
  TEN(10);

  private final Integer nominal;

  Banknote(Integer value) {
    this.nominal = value;
  }

  public Integer getNominal() {
    return nominal;
  }
}
