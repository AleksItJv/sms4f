package ua.sms4f.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users") //, uniqueConstraints= @UniqueConstraint(columnNames={"id", "login"}))
public class UserDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(name = "login", unique = true)
    private String login;
    private String password;
    private String position;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public UserDB() {
    }
    public UserDB(String login, String password, String position, Set<Role> roles) {
        this.login = login;
        this.password = password;
        this.position = position;
        this.roles = roles;
    }

    public UserDB(long id, String login, String password, String position, Set<Role> roles) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.position = position;
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getStringRoles(UserDB user){
        return user.getRoles().toString().replaceAll("^\\[|\\]$", "");
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserDB{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", position='" + position + '\'' +
                ", roles=" + roles +
                '}';
    }
}
