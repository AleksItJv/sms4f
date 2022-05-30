package ua.sms4f.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.sms4f.entity.ClientsDetails;
import ua.sms4f.entity.UserDB;

import java.util.Optional;

@Repository
public interface ClientsDetailsRepository extends JpaRepository<ClientsDetails, Long> {

    ClientsDetails findByUserDB(UserDB userDB);

    @Override
    Optional<ClientsDetails> findById(Long aLong);

}
