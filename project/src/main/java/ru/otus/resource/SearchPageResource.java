package ru.otus.resource;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import java.util.UUID;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.repository.VacancyRepository;

@Controller
public class SearchPageResource {

  private final VacancyRepository vacancyRepository;

  public SearchPageResource(VacancyRepository vacancyRepository) {
    this.vacancyRepository = vacancyRepository;
  }

  @GetMapping
  @RequestMapping("/search")
  public String getSearchPage(@RequestParam(required = false, defaultValue = "") String title,
                              Model model,
                              HttpServletResponse response) {
    model.addAttribute("title", title);
    model.addAttribute("vacancies", vacancyRepository.getVacanciesByTitle(title));
    response.addCookie(new Cookie("auth", UUID.randomUUID().toString()));
    return "searchPage";
  }

}
