package ru.otus.resource;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.otus.model.Vacancy;
import ru.otus.repository.VacancyRepository;

@Controller
public class VacancyResource {

  private final VacancyRepository vacancyRepository;

  public VacancyResource(VacancyRepository vacancyRepository) {
    this.vacancyRepository = vacancyRepository;
  }

  @GetMapping("vacancy")
  @ResponseBody
  public List<Vacancy> getVacanciesBy(@RequestParam(required = false) String title) {
    title = title == null ? "" : title;
    return vacancyRepository.getVacanciesByTitle(title);
  }

  @GetMapping("/add")
  public String getAddPage(Model model) {
    model.addAttribute("vacancy", new Vacancy());
    return "addPage";
  }

  @PostMapping("/add")
  public String addVacancy(@ModelAttribute Vacancy vacancy) {
    vacancyRepository.save(vacancy);
    return "redirect:/";
  }

  @GetMapping("/edit/{id}")
  public String getEditPage(@PathVariable Long id, Model model) {
    var vacancy = vacancyRepository.getVacancyById(id);
    if (vacancy.isEmpty()) {
      return "redirect:/";
    }
    model.addAttribute("vacancy", vacancy.get());
    return "editPage";
  }

  @PostMapping("/edit/{id}")
  public String editVacancy(@PathVariable Long id, @ModelAttribute Vacancy vacancy) {
    if (vacancyRepository.existsById(id)) {
      vacancyRepository.save(vacancy);
    }
    return "redirect:/";
  }

  @PostMapping("/delete/{id}")
  public String deleteVacancy(@PathVariable Long id) {
    vacancyRepository.deleteById(id);
    return "redirect:/";
  }
}
