package com.example.demo.web.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data

public class CreateWordRequest {
    private String word;
    private String definition;
    @JsonCreator
    public CreateWordRequest(@JsonProperty String word,@JsonProperty String definition){
        this.word=word;
        this.definition=definition;
    }

}
