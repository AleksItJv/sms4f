package ua.sms4f.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.sms4f.dto.UserCreationDTO;
import ua.sms4f.entity.UserDB;
import ua.sms4f.repository.UserDBRepository;
import ua.sms4f.service.CustomUserDBDetailsService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    private UserDBRepository userDBRepository;

    @Autowired
    private CustomUserDBDetailsService customUserDBDetailsService;

    @GetMapping("/")
    public String admin(/*Authentication authentication, Model model*/) {
        /*model.addAttribute("users", userDBRepository.findAll());*/
        return "admin/admin";
    }

    @GetMapping("/all")
    public String allUsers(Model model){
        model.addAttribute("users", userDBRepository.findAll());
        return "/admin/allUsers";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {

        UserCreationDTO usersForm = new UserCreationDTO();
        usersForm.addUser(new UserDB());
        /*for (int i = 1; i <= 3; i++) {
            if (usersForm.getUsers() != null) {
                usersForm.addUser(new UserDB());
            }
        }*/
        model.addAttribute("form", usersForm);
        return "admin/createUsersForm";
    }

    @PostMapping("/save")
    public String saveBooks(@ModelAttribute UserCreationDTO form, Model model) {

        customUserDBDetailsService.saveAll(form.getUsers());
        model.addAttribute("users", customUserDBDetailsService.findAll());

        return "redirect:/admin/all";
    }

    @GetMapping("/edit")
    public String showEditForm(Model model) {
        List<UserDB> users = new ArrayList<>();
        customUserDBDetailsService.findAll().iterator().forEachRemaining(users::add);

        model.addAttribute("form", new UserCreationDTO(users));
        return "admin/editUsersForm";
    }
}

