package pw.backend.reactbackend.mapper;

import org.springframework.jdbc.core.RowMapper;
import pw.backend.reactbackend.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int arg1) throws SQLException {
        User emp = new User();
        emp.setUserId(rs.getString("UserId"));
        emp.setUserLogin(rs.getString("UserLogin"));
        emp.setUserName(rs.getString("UserName"));
        emp.setUserLastName(rs.getString("UserLastName"));
        emp.setUserBirth(rs.getString("UserBirth"));

        return emp;
    }


}