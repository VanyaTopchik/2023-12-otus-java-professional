package ru.otus.resource;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.otus.dto.UserRegistrationDto;
import ru.otus.service.UserDetailsServiceImpl;

@Controller
@RequestMapping("/registration")
public class UserRegistrationResource {
  private final UserDetailsServiceImpl userDetailsServiceImpl;

  public UserRegistrationResource(UserDetailsServiceImpl userDetailsServiceImpl) {
    this.userDetailsServiceImpl = userDetailsServiceImpl;
  }

  @ModelAttribute("user")
  public UserRegistrationDto userRegistrationDto() {
    return new UserRegistrationDto();
  }

  @GetMapping
  public String showRegistrationForm() {
    return "registration";
  }

  @PostMapping
  public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
    try {
      userDetailsServiceImpl.loadUserByUsername(registrationDto.getLogin());
      return "redirect:/registration?error";
    } catch (UsernameNotFoundException e) {
      userDetailsServiceImpl.save(registrationDto);
      return "redirect:/registration?success";
    }
  }
}
