package ru.otus.crm.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "client")
public class Client implements Cloneable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name")
  private String name;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  @JoinColumn(name = "address_id")
  private Address address;

  @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  private List<Phone> phones;

  public Client(String name) {
    this.id = null;
    this.name = name;
  }

  public Client(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Client(Long id, String name, Address address, List<Phone> phones) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.phones = phones;

    if (this.phones != null) {
      for (Phone phone : this.phones) {
        phone.setClient(this);
      }
    }
  }

  @Override
  public Client clone() {
    return new Client(
        this.id,
        this.name,
        this.address == null ? null : address.clone(),
        this.phones == null ? null : List.copyOf(this.phones.stream().map(Phone::clone).toList())
    );
  }

  @Override
  public String toString() {
    return "Client{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", address=" + address +
        ", phones=" + phones +
        '}';
  }
}
