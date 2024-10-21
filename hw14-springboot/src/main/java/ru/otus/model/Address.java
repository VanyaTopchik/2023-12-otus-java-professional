package ru.otus.model;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@ToString
@Table(name = "address")
public class Address {

  @Id
  @Column(value = "id")
  private final Long id;

  @Column(value = "street")
  private final String street;

  @Column(value = "client_id")
  private final Long clientId;

  @PersistenceCreator
  public Address(Long id, String street, Long clientId) {
    this.id = id;
    this.street = street;
    this.clientId = clientId;
  }

  public Address(String street, Long clientId) {
    this(null, street, clientId);
  }

  @Override
  public Address clone() {
    return new Address(this.id, this.street, this.clientId);
  }

}