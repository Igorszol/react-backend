package pw.backend.reactbackend.dao;

import pw.backend.reactbackend.entity.User;

import java.util.List;

public interface UserDao {
	List<User> findAll();
	void insertUser(User us);
}
