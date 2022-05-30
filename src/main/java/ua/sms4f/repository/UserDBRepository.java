package ua.sms4f.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;
import ua.sms4f.entity.UserDB;

import java.util.Optional;

@Repository
public interface UserDBRepository extends JpaRepository<UserDB, Long> {

    UserDB findByLogin(String login);
    Optional<UserDB> findById(Long aLong);
}