package com.example.demo.web.dto;

import com.example.demo.web.entity.Word;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WordDto {

    private Long id;
    private String word;
    private String definition;

    public static WordDto from(Word word) {
        WordDto result = new WordDto();
        result.id = word.getId();
        result.word = word.getWord();
        result.definition = word.getDefinition();
        return result;
    }
}
