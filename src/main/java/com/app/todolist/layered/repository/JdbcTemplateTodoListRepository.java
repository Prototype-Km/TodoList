package com.app.todolist.layered.repository;


import com.app.todolist.layered.dto.TodoListResponseDTO;
import com.app.todolist.layered.entity.TodoList;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class JdbcTemplateTodoListRepository implements TodoListRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateTodoListRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public TodoListResponseDTO save(TodoList todoList) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("todolist").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("writer",todoList.getWriter());
        parameters.put("password",todoList.getPassword());
        parameters.put("contents",todoList.getContents());
        parameters.put("createdDate",todoList.getCreateDate());
        parameters.put("updatedDate",todoList.getUpdateDate());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new TodoListResponseDTO(key.longValue(),todoList.getWriter(),todoList.getContents(),todoList.getCreateDate(),todoList.getUpdateDate() );
    }


//
//        //저장 후 생성된 Key 값을 Number타입으로 반환하는 메소드
//        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
//
//        return new MemoResponseDTO(key.longValue(), memo.getTitle() ,memo.getContents() );
//    }
//

//    private RowMapper<MemoResponseDTO> memoRowMapper(){
//        return new RowMapper<MemoResponseDTO>() {
//            @Override
//            public MemoResponseDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
//                return new MemoResponseDTO(
//                        rs.getLong("id"),
//                        rs.getString("title"),
//                        rs.getString("contents")
//                );
//            }
//        };
//    }

//    private RowMapper<Memo> memoRowMapperV2(){
//        return new RowMapper<Memo>() {
//            @Override
//            public Memo mapRow(ResultSet rs, int rowNum) throws SQLException {
//                return new Memo(
//                        rs.getLong("id"),
//                        rs.getString("title"),
//                        rs.getString("contents")
//                );
//            }
//        };
//    }

}


