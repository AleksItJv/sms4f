package ua.sms4f.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.sms4f.entity.ClientsDetails;
import ua.sms4f.repository.ClientsDetailsRepository;
import ua.sms4f.service.ClientsDetailsService;

@Controller
@RequestMapping("/clients")
public class ClientsController {

    public ClientsDetailsService clientsDetailsService;

    @GetMapping("/allClients")
    public String allClients(Model model){
        model.addAttribute("clients", clientsDetailsService.findAll());
        return "/clients/allClients";
    }

    @Autowired
    public void setClientsDetailsService(ClientsDetailsService clientsDetailsService) {
        this.clientsDetailsService = clientsDetailsService;
    }
}
