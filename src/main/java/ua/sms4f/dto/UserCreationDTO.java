package ua.sms4f.dto;

import org.springframework.stereotype.Component;
import ua.sms4f.entity.UserDB;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserCreationDTO {

    private List<UserDB> users = new ArrayList<>();

    public UserCreationDTO() {
    }

    public UserCreationDTO(List<UserDB> users) {
        this.users = users;
    }

    public void addUser(UserDB user){
        this.users.add(user);
    }

    public List<UserDB> getUsers() {
        return users;
    }

    public void setUsers(List<UserDB> users) {
        this.users = users;
    }
}