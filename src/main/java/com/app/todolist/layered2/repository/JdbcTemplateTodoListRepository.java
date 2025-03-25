package com.app.todolist.layered2.repository;


import com.app.todolist.layered2.dto.TodoListRequestDTO2;
import com.app.todolist.layered2.dto.TodoListResponseDTO2;
import com.app.todolist.layered2.entity.TodoList2;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
public class JdbcTemplateTodoListRepository implements TodoListRepository2 {

    //날짜 String 형변환
    final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateTodoListRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //일정 등록  -> 아이디 비번 맞으면 -> 작성가능
    @Override
    public TodoListResponseDTO2 saveTodoList(TodoList2 todoList) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("tbl_todolist").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userId", todoList.getUserId());
        parameters.put("contents", todoList.getContents());
        parameters.put("createdDate", todoList.getCreatedDate());
        parameters.put("updatedDate", todoList.getUpdatedDate());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        //builder
        return TodoListResponseDTO2.builder()
                .id(key.longValue())
                .userId(todoList.getUserId())
                .contents(todoList.getContents())
                .createdDate(todoList.getCreatedDate().format(FORMATTER))
                .updatedDate(todoList.getUpdatedDate().format(FORMATTER))
                .build();
    }


     //전체 조회
    @Override
    public List<TodoListResponseDTO2> findAllTodoList() {
        return jdbcTemplate.query("SELECT * FROM todolist ORDER BY updatedDate , writer DESC;",todoListRowMapper());
    }

    //전체 조회 (userId로 조회)
    @Override
    public List<TodoListRequestDTO2> findAllByUserId(Long userId) {
        return List.of();
    }


    // 상세보기
    @Override
    public Optional<TodoListResponseDTO2> findById(Long id) {
        List<TodoListResponseDTO2> result = jdbcTemplate.query(
                "SELECT * FROM todolist WHERE id = ?",todoListRowMapper(),id);
        return result.stream().findAny();
    }

//   예외처리
    @Override
    public TodoList2 findTodoByIdOrElseThrow(Long id) {
        List<TodoList2> result = jdbcTemplate.query("SELECT * FROM todolist WHERE id = ?", todoListRowMapperV2(), id);
        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exist id ="+id));
    }

    //수정하기 (작성자 or 일정)
    @Override
    public int updateWriterOrContents(Long id, String writer, String contents) {
        return jdbcTemplate.update("UPDATE todolist  SET writer = ?,contents = ?,updatedDate = ? WHERE id = ?",writer,contents, LocalDateTime.now(),id);
    }

    @Override
    public int deleteTodoList(Long id) {
        return jdbcTemplate.update("DELETE FROM todolist WHERE id = ?",id);
    }


    private RowMapper<TodoList2> todoListRowMapperV2(){
        return new RowMapper<TodoList2>() {
            @Override
            public TodoList2 mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new TodoList2(
                        rs.getLong("id"),
                        rs.getLong("userId"),
                        rs.getString("contents"),
                        rs.getTimestamp("createdDate").toLocalDateTime(),
                        rs.getTimestamp("updatedDate").toLocalDateTime()
                );
            }
        };
    }

    private RowMapper<TodoListResponseDTO2> todoListRowMapper() {
        return new RowMapper<TodoListResponseDTO2>() {
            @Override
            public TodoListResponseDTO2 mapRow(ResultSet rs, int rowNum) throws SQLException {
                return TodoListResponseDTO2.builder()
                        .id(rs.getLong("id"))
                        .userId(rs.getLong("userId"))
                        .userEmail(rs.getString("email"))
                        .userName(rs.getString("name"))
                        .contents(rs.getString("contents"))
                        .createdDate(rs.getTimestamp("createdDate").toLocalDateTime().format(FORMATTER))
                        .updatedDate(rs.getTimestamp("updatedDate").toLocalDateTime().format(FORMATTER))
                        .build();
            }
        };

    }


}


