package pw.backend.reactbackend.service;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import pw.backend.reactbackend.dao.UserDao;
import pw.backend.reactbackend.entity.User;

@Component
public class UserServiceImpl implements UserService{

    @Resource
    UserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void insertEmployee(User us) {
userDao.insertUser(us);
    }
}
