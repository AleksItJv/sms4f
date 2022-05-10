package ua.sms4f.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.sms4f.entity.UserDB;

@Repository
public interface UserDBRepository extends JpaRepository<UserDB, Long> {

    UserDB findByLogin(String login);

}