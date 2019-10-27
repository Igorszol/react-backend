package pw.backend.reactbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import pw.backend.reactbackend.entity.User;
import pw.backend.reactbackend.repository.UserRepository;

import java.util.ArrayList;

@org.springframework.stereotype.Service
public class UserService {
    @Autowired
    UserRepository repository;


    public User checkLogin(String login) {
        ArrayList<User> users;
        users = (ArrayList<User>) repository.findAll();
        for (User user : users) {
            if (user.getLogin() != null && user.getLogin().equals(login))
                return user;
        }
        return null;
    }
}
