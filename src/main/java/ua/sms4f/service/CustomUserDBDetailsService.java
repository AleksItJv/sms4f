package ua.sms4f.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ua.sms4f.entity.UserDB;

import java.util.List;

public interface CustomUserDBDetailsService extends UserDetailsService {

    List<UserDB> findAll();
    void saveAll(List<UserDB> users);
}
