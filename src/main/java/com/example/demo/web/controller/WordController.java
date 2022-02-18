package com.example.demo.web.controller;

import com.example.demo.service.WordService;
import com.example.demo.web.dto.CreateWordRequest;
import com.example.demo.web.dto.WordDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/word")

public class WordController {
    private final WordService wordService;

    @GetMapping
    public ResponseEntity<?> getWord(@RequestParam("word") String wordParam) {
        List<WordDto> dtos = wordService.findWord(wordParam);
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
    @PostMapping
    public ResponseEntity<?> createWord(@RequestBody CreateWordRequest createWordRequest){
        WordDto createWord = wordService.createWord(createWordRequest);
        return ResponseEntity.status(HttpStatus.OK).body(createWord);
    }
}
