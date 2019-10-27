package pw.backend.reactbackend.dao;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import pw.backend.reactbackend.entity.User;
import pw.backend.reactbackend.mapper.UserRowMapper;


@Repository
public class UserDaoImpl implements UserDao {
    public UserDaoImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    NamedParameterJdbcTemplate template;

    @Override
    public List<User> findAll() {
        return template.query("select * from employee", new UserRowMapper());
    }

    @Override
    public void insertUser(User us) {
        final String sql = "insert into employee(UserId, UserLogin, UserName, UserLastName, UserBirth) values(:UserId, :UserLogin, :UserName, :UserLastName, :UserBirth)";

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("UserId", us.getUserId())
                .addValue("UserLogin", us.getUserLogin())
                .addValue("UserName", us.getUserName())
                .addValue("UserLastName", us.getUserLastName())
        .addValue("UserBirth", us.getUserLastName());
        template.update(sql,param, holder);
    }
}