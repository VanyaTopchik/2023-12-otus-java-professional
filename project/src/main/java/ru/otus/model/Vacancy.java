package ru.otus.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Setter
@Getter
@Table("vacancies")
public class Vacancy {

  @Id
  private Integer id;

  @Column("title")
  private String title;

  @Column("description")
  private String description;

  public Vacancy() {
  }

  public Vacancy(String title, String description) {
    this.title = title;
    this.description = description;
  }
}
