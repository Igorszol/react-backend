package pw.backend.reactbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import pw.backend.reactbackend.entity.User;
import pw.backend.reactbackend.exceptions.ResourceNotFoundException;
import pw.backend.reactbackend.repository.UserRepository;

import java.util.Optional;

@org.springframework.stereotype.Service
public class UserService {
    @Autowired
    UserRepository repository;


    public User FindByLogin(String login) {
        User user = repository.findByLogin(login);
        if(user!=null)
            return user;
        throw new ResourceNotFoundException(String.format("User with login [%s] not found.", login));
    }

    public User updateUser(User updatedUser) {
        User user=FindByLogin(updatedUser.getLogin());
        if (user!=null) {
            return repository.save(updatedUser);
        }
        throw new ResourceNotFoundException(String.format("User with login [%s] not found.", updatedUser.getLogin()));
    }
}
