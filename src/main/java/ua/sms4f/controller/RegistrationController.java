package ua.sms4f.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.sms4f.entity.Role;
import ua.sms4f.entity.UserDB;
import ua.sms4f.repository.UserDBRepository;

import java.util.Map;
import java.util.Set;

@Controller
public class RegistrationController {

    private UserDBRepository userDBRepository;

    @GetMapping("/registration")
    public String registration(@RequestParam(value = "name", required = false) String requestName, Map<String, Object> model){
        if (requestName != null && requestName.equalsIgnoreCase("Joe")){
            model.put("message", "Start Registration Joe");
        }
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(UserDB user, Map<String, Object> model){
        System.out.println("USER " + user);
        System.out.println("MODEL " + model);

        UserDB userFromDB = userDBRepository.findByLogin(user.getLogin());
        if(userFromDB != null){
            model.put("message", "User already exists");
            return "registration";
        }
        user.setPosition("new user");
        user.setRoles(Set.of(Role.USER));
        userDBRepository.save(user);

        return "redirect:/login";
    }

    @Autowired
    public void setUserDBRepository(UserDBRepository userDBRepository){
        this.userDBRepository = userDBRepository;
    }

}