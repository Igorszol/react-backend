package pw.backend.reactbackend.service;

import pw.backend.reactbackend.entity.User;

import java.util.List;


public interface UserService {
    List<User> findAll();

    void insertEmployee(User us);
}
