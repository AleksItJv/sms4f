package ua.sms4f.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

@Controller
public class HelloController {

    @GetMapping("/")
    public String hello(Model model) {

        model.addAttribute("title", "Main Page");
        model.addAttribute("text", "Next block -->");

        return "index";
    }

    @GetMapping("/index")
    public String index(){
        return "redirect:/";
    }

    @GetMapping("/deny")
    public String deny(){
        return "/deny";
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("title", "Home page");
        return "home";
    }
}
