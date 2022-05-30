package ua.sms4f.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Entity(name = "clients_details")
@Getter
@Setter
public class ClientsDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Fetch(FetchMode.JOIN)
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserDB userDB;

    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "identification_number")
    private Long identificationNumber;
    @Column(name = "passport")
    private String passport;
    @Column(name = "city")
    private String city;

    public ClientsDetails() {
    }

    public ClientsDetails(UserDB userDB) {
        this.userDB = userDB;
    }

    @Override
    public String toString() {
        return "ClientsDetails{" +
                "id=" + id +
                ", userDB=" + userDB +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", identificationNumber=" + identificationNumber +
                ", passport='" + passport + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
