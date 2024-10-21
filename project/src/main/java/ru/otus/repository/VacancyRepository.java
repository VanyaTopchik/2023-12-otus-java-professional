package ru.otus.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.otus.model.Vacancy;

@Repository
public interface VacancyRepository extends ListCrudRepository<Vacancy, Long> {

  @Query("select * from vacancies v where lower(v.title) like concat('%', lower(:title),'%')")
  List<Vacancy> getVacanciesByTitle(@Param("title") String title);

  Optional<Vacancy> getVacancyById(Long id);
}
