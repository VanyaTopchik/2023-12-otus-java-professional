package ru.otus.model;

import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@ToString
@Table(name = "phone")
public class Phone {

  @Id
  @Column(value = "id")
  private final Long id;

  @Column(value = "number")
  private final String number;

  private final Long clientId;

  @PersistenceCreator
  public Phone(Long id, String number, Long clientId) {
    this.id = id;
    this.number = number;
    this.clientId = clientId;
  }

  @PersistenceCreator
  public Phone(String number, Long clientId) {
    this(null, number, clientId);
  }

  @Override
  public Phone clone() {
    return new Phone(this.id, this.number, this.clientId);
  }

}