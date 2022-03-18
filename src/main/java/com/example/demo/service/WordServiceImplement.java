package com.example.demo.service;

import com.example.demo.exceptions.EmptyWordException;
import com.example.demo.exceptions.WordNotFoundException;
import com.example.demo.repo.WordRepository;
import com.example.demo.web.dto.CreateWordRequest;
import com.example.demo.web.dto.WordDto;
import com.example.demo.web.entity.Word;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo.web.dto.WordDto.from;

@AllArgsConstructor
@Service

public class WordServiceImplement implements WordService {
    private final WordRepository wordRepository;

    @Override
    public List<WordDto> findWord(String wordRequest) throws WordNotFoundException, EmptyWordException {
        if (wordRequest.isEmpty()) {
            throw new EmptyWordException("Vasea esti dalbaiob !!!!!!!!!!!!");
        }
        List<Word> foundWords = wordRepository.findAllByWord(wordRequest);
        if (foundWords.isEmpty()) {
            //throw new WordNotFoundException("\""+wordRequest.getWord()+"\""+" not found");
            throw new WordNotFoundException(String.format("Word [%s] not found", wordRequest));
        }

        List<WordDto> resultList = new ArrayList<>();
        for (Word word : foundWords) {
            resultList.add(from(word));
        }



        return resultList;
    }

    @Override
    public WordDto createWord(CreateWordRequest createWordRequest) {
        Word word = Word.builder().word(createWordRequest.getWord()).definition(createWordRequest.getDefinition()).build();
        Word save = wordRepository.save(word);

        return from(save);
    }
}
