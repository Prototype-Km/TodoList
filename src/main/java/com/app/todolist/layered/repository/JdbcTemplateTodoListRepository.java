package com.app.todolist.layered.repository;


import com.app.todolist.layered.dto.TodoListResponseDTO;
import com.app.todolist.layered.entity.TodoList;
import lombok.extern.slf4j.Slf4j;
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
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
public class JdbcTemplateTodoListRepository implements TodoListRepository {

    //날짜 String 형변환
    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateTodoListRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //일정 등록
    @Override
    public TodoListResponseDTO save(TodoList todoList) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("todolist").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("writer", todoList.getWriter());
        parameters.put("password", todoList.getPassword());
        parameters.put("contents", todoList.getContents());
        parameters.put("createdDate", todoList.getCreatedDate());
        parameters.put("updatedDate", todoList.getUpdatedDate());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        //builder
        return TodoListResponseDTO.builder()
                .id(key.longValue())
                .writer(todoList.getWriter())
                .contents(todoList.getContents())
                .createdDate(todoList.getCreatedDate().format(FORMATTER))
                .updatedDate(todoList.getUpdatedDate().format(FORMATTER))
                .build();

    }

    /**
     * 전체 조회
     */
    @Override
    public List<TodoListResponseDTO> findAll() {
        return jdbcTemplate.query("SELECT * FROM todolist ORDER BY updatedDate , writer DESC;",todoListRowMapper());
    }

    /**
     * TodoList id로 조회
     */
    @Override
    public Optional<TodoListResponseDTO> findById(Long id) {
        List<TodoListResponseDTO> result = jdbcTemplate.query(
                "SELECT * FROM todolist WHERE id = ?",todoListRowMapper(),id);
        return result.stream().findAny();
    }

//   예외처리
    @Override
    public TodoList findTodoByIdOrElseThrow(Long id) {
        List<TodoList> result = jdbcTemplate.query("select * from todolist where id = ?", todoListRowMapperV2(), id);
        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exist id ="+id));
    }


    private RowMapper<TodoList> todoListRowMapperV2(){
        return new RowMapper<TodoList>() {
            @Override
            public TodoList mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new TodoList(
                        rs.getLong("id"),
                        rs.getString("password"),
                        rs.getString("writer"),
                        rs.getString("contents"),
                        rs.getTimestamp("createdDate").toLocalDateTime(),
                        rs.getTimestamp("updatedDate").toLocalDateTime()
                );
            }
        };
    }

    private RowMapper<TodoListResponseDTO> todoListRowMapper() {
        return new RowMapper<TodoListResponseDTO>() {
            @Override
            public TodoListResponseDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                return TodoListResponseDTO.builder()
                        .id(rs.getLong("id"))
                        .writer(rs.getString("writer"))
                        .contents(rs.getString("contents"))
                        .createdDate(rs.getTimestamp("createdDate").toLocalDateTime().format(FORMATTER))
                        .updatedDate(rs.getTimestamp("updatedDate").toLocalDateTime().format(FORMATTER))
                        .build();
            }
        };

    }


}


