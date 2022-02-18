package com.example.demo.web.dto;

import com.example.demo.web.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private Long id;
    private String email;

    public static UserDto createUser(User user){
        UserDto result = new UserDto();
        result.email = result.getEmail();
        return result;
    }

}
