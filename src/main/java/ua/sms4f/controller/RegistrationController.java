package ua.sms4f.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.sms4f.entity.ClientsDetails;
import ua.sms4f.entity.Role;
import ua.sms4f.entity.UserDB;
import ua.sms4f.repository.ClientsDetailsRepository;
import ua.sms4f.repository.UserDBRepository;
import ua.sms4f.service.ClientsDetailsService;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import static ua.sms4f.configuration.CustomAuthenticationProvider.getCurrentUsername;

@Controller
public class RegistrationController {

    private UserDBRepository userDBRepository;
    private ClientsDetailsService clientsDetailsService;

    @GetMapping("/registration")
    public String registration(@RequestParam(value = "name", required = false) String requestName, Map<String, Object> model) {
        if (requestName != null && requestName.equalsIgnoreCase("Joe")) {
            model.put("message", "Start Registration Joe");
        }
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(UserDB user, Map<String, Object> model) {
        System.out.println("USER " + user);
        System.out.println("MODEL " + model);

        UserDB userFromDB = userDBRepository.findByLogin(user.getLogin());
        if (userFromDB != null) {
            model.put("message", "User already exists");
            return "registration";
        }
        user.setPosition("new user");
        user.setRoles(Set.of(Role.USER));
        userDBRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/registration/step1")
    public String getStep1(Model model) {

        if (getCurrentUsername().equals("anonymousUser")) {
            return "login";
        }
        Optional<UserDB> userFromDB = Optional.of(userDBRepository.findByLogin(getCurrentUsername()));
        model.addAttribute("clientsDetails", new ClientsDetails(userFromDB.orElseThrow(() -> new NoSuchElementException())));
        return "step1";
    }

    @PostMapping("/registration/step1")
    public String postStep1(@ModelAttribute ClientsDetails clientsDetails, Model model) throws Exception {

        clientsDetailsService.updateClient(clientsDetails);
        model.addAttribute("clientsDetails2", clientsDetails);
        return "step2";
    }

    @PostMapping("/registration/step2")
    public String postStep2(@ModelAttribute ClientsDetails clientsDetails, Model model) throws Exception {

        clientsDetailsService.updateClient(clientsDetails);
        model.addAttribute("clientsDetails2", clientsDetails);
        return "redirect:/clients/allClients";
    }

    @Autowired
    public void setUserDBRepository(UserDBRepository userDBRepository) {
        this.userDBRepository = userDBRepository;
    }

    @Autowired
    public void setClientsDetailsService(ClientsDetailsService clientsDetailsService) {
        this.clientsDetailsService = clientsDetailsService;
    }

}