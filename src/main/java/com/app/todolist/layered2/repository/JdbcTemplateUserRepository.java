package com.app.todolist.layered2.repository;

import com.app.todolist.layered2.dto.TodoListResponseDTO2;
import com.app.todolist.layered2.dto.UserRequestDTO;
import com.app.todolist.layered2.dto.UserResponseDTO;
import com.app.todolist.layered2.entity.TodoList2;
import com.app.todolist.layered2.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
public class JdbcTemplateUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateUserRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public UserResponseDTO insert(User user) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("tbl_user").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("user_name", user.getUserName());
        parameters.put("user_password", user.getUserPassword());
        parameters.put("user_email", user.getUserEmail());
        parameters.put("created_date", user.getCreatedDate());
        parameters.put("updated_date", user.getUpdatedDate());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        //builder 패턴
        return UserResponseDTO.builder()
                .id(key.longValue())
                .userName(user.getUserName())
                .userEmail(user.getUserEmail())
                .createdDate(user.getCreatedDate())
                .updatedDate(user.getUpdatedDate())
                .build();
    }

    @Override
    public Optional<User> findByUserEmailAndPassword(String userEmail, String userPassword) {
        List<User> result = jdbcTemplate.query(
                "SELECT * FROM tbl_user WHERE user_email = ? AND user_password = ?", userRowMapper(), userEmail, userPassword);
        return result.stream().findAny();
    }

    @Override
    public User findByIdOrElseThrow(Long id) {
        List<User> result = jdbcTemplate.query("SELECT * FROM tbl_user WHERE id = ?",userRowMapper(),id);
        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exist id ="+id));
    }

    @Override
    public List<UserResponseDTO> findAll() {
        return jdbcTemplate.query("SELECT id,user_name,user_email,created_date,updated_date from tbl_user",userResponseDTORowMapper());
    }


    private RowMapper<User> userRowMapper(){
        return new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                return User.builder()
                        .id(rs.getLong("id"))
                        .userName(rs.getString("user_name"))
                        .userEmail(rs.getString("user_email"))
                        .userPassword(rs.getString("user_password"))
                        .createdDate(rs.getTimestamp("created_date").toLocalDateTime())
                        .updatedDate(rs.getTimestamp("updated_date").toLocalDateTime())
                        .build();
            }
        };
    }

    private RowMapper<UserResponseDTO> userResponseDTORowMapper(){
        return new RowMapper<UserResponseDTO>() {
            @Override
            public UserResponseDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                return UserResponseDTO.builder()
                        .id(rs.getLong("id"))
                        .userName(rs.getString("user_name"))
                        .userEmail(rs.getString("user_email"))
                        .createdDate(rs.getTimestamp("created_date").toLocalDateTime())
                        .updatedDate(rs.getTimestamp("updated_date").toLocalDateTime())
                        .build();
            }
        };
    }
}