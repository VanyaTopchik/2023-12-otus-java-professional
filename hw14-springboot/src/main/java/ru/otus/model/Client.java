package ru.otus.model;

import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@ToString
@Table(name = "client")
public class Client implements Cloneable {

  @Id
  @Column(value = "id")
  private final Long id;

  @Column(value = "name")
  private final String name;

  @MappedCollection(idColumn = "client_id")
  private final Address address;

  @MappedCollection(idColumn = "client_id")
  private final Set<Phone> phones;

  public String getPhonesAsString() {
    return this.phones.stream().map(Phone::getNumber).collect(Collectors.joining(", "));
  }

  @PersistenceCreator
  public Client(Long id, String name, Address address, Set<Phone> phones) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.phones = phones;
  }

  public Client(String name, Address address, Set<Phone> phones) {
    this(null, name, address, phones);
  }

  @Override
  public Client clone() {
    return new Client(
        this.id,
        this.name,
        this.address == null ? null : address.clone(),
        this.phones == null ? null : this.phones.stream().map(Phone::clone).collect(Collectors.toSet()));
  }

}
