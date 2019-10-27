package pw.backend.reactbackend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pw.backend.reactbackend.entity.User;
import pw.backend.reactbackend.service.UserService;

@Repository
abstract class UserRepositoryImpl implements UserRepository {

    @Autowired
    UserService service;

    @Override
    public User findByLogin(String login) {
        return service.checkLogin(login);

    }
}