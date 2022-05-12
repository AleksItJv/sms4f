package ua.sms4f.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.sms4f.entity.UserDB;
import ua.sms4f.repository.UserDBRepository;
import ua.sms4f.service.CustomUserDBDetailsService;

import java.util.List;

@Service
public class CustomUserDBDetailsServiceImpl implements CustomUserDBDetailsService {

    private UserDBRepository userDBRepository;

    @Override
    public List<UserDB> findAll() {
        return userDBRepository.findAll();
    }

    @Override
    public void saveAll(List<UserDB> users) {
        userDBRepository.saveAll(users);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDB userDB = userDBRepository.findByLogin(username);
        if (userDB == null) {
            throw new UsernameNotFoundException("Unknown user: " + username);
        }

        UserDetails userDetails = User.builder()
                .username(userDB.getLogin())
                .password(userDB.getPassword())
                .roles(String.valueOf(userDB.getRoles()))
                .build();

        return userDetails;
    }

    @Autowired
    public void setUserDBRepository(UserDBRepository userDBRepository) {
        this.userDBRepository = userDBRepository;
    }
}
