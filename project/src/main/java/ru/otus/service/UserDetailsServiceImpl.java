package ru.otus.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.otus.dto.UserRegistrationDto;
import ru.otus.model.Authority;
import ru.otus.model.User;
import ru.otus.model.UserDetailsImpl;
import ru.otus.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  public void save(UserRegistrationDto registrationDto) {
    User user = new User(registrationDto.getLogin(), passwordEncoder.encode(registrationDto.getPassword()), Authority.USER.getRole());
    userRepository.save(user);
  }

  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findByLogin(username);
    return user.map(UserDetailsImpl::new)
        .orElseThrow(() -> new UsernameNotFoundException(username + " not found in repo."));
  }

}