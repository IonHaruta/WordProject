package com.example.demo.web.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data

public class LogInRequest {
    private String email;
    private String password;
    @JsonCreator
    public LogInRequest(@JsonProperty String email,@JsonProperty String password) {
        this.email = email;
        this.password = password;
    }
}
