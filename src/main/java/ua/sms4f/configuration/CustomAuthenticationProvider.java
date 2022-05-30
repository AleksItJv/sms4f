package ua.sms4f.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ua.sms4f.entity.UserDB;
import ua.sms4f.repository.UserDBRepository;

import java.util.Set;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDBRepository userDBRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDB userDB = userDBRepository.findByLogin(username);
        if (userDB == null) {
            System.out.println("Unknown user: " + username);
            throw new BadCredentialsException("Unknown user: " + username);
        }
        if (!password.equals(userDB.getPassword())) {
            System.out.println("Unknown user: " + username);
            throw new BadCredentialsException("Bad password");
        }

        UserDetails principal = User.builder()
                .username(userDB.getLogin())
                .password(userDB.getPassword())
                .authorities(userDB.getRoles())
                //.roles(userDB.getRoles().toString())
                .build();
        return new UsernamePasswordAuthenticationToken(
                principal, password, principal.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    public static String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
        //authorities - ROLE_ANONYMOUS
        //principal - anonymousUser
    }
}
