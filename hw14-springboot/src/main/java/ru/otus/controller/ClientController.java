package ru.otus.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.dto.ClientDto;
import ru.otus.model.Address;
import ru.otus.model.Client;
import ru.otus.model.Phone;
import ru.otus.service.DBServiceClient;

@Controller
public class ClientController {
  private static final Logger log = LoggerFactory.getLogger(ClientController.class);
  private final DBServiceClient clientService;

  public ClientController(DBServiceClient clientService) {
    this.clientService = clientService;
  }

  @GetMapping("/")
  public String index(){
    return "index";
  }

  @PostMapping("/client/save")
  public RedirectView save(ClientDto dto) {
    log.info("{}", dto);
    clientService.saveClient(toClient(dto));
    return new RedirectView("/client/list", true);
  }

  @GetMapping("/client/list")
  public String getAll(Model model) {
    log.info("{}", model);
    List<Client> clients = clientService.findAll();
    model.addAttribute("client", new ClientDto());
    model.addAttribute("clients", clients);
    return "clients";
  }

  private Client toClient(ClientDto dto) {
    Set<Phone> phones = new HashSet<>();
    if (StringUtils.isNotBlank(dto.getPhones())) {
      Arrays.stream(dto.getPhones().split(",")).forEach(number -> phones.add(new Phone(number, null)));
    }
    Address address = null;
    if (StringUtils.isNotBlank(dto.getStreet())) {
      address = new Address(dto.getStreet(), null);
    }
    return new Client(dto.getName(), address, phones);
  }

}