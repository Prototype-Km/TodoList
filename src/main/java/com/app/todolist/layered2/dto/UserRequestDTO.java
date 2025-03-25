package com.app.todolist.layered2.dto;

import com.app.todolist.layered2.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
//    @NotBlank(message = "이름을 작성해주세요.")
    private String userName;

    @NotBlank(message = "이메일을 작성해주세요.")
    @Email(message = "이메일 형식이 틀렸습니다.")
    private String userEmail;
    @NotBlank(message = "비밀번호를 작성해주세요.")
    private String userPassword;

    // DTO → Entity 변환
    public User toEntity() {
        return User.builder()
                .userName(this.userName)
                .userEmail(this.userEmail)
                .userPassword(this.userPassword)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
    }
}
