package com.example.demo.service;

import com.example.demo.web.dto.CreateWordRequest;
import com.example.demo.web.dto.GetWordRequest;
import com.example.demo.web.dto.WordDto;

import java.util.List;

public interface WordService {
    List<WordDto> findWord(String wordRequest);
    WordDto createWord(CreateWordRequest createWordRequest);

}
