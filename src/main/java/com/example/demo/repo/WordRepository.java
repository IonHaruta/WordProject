package com.example.demo.repo;

import com.example.demo.web.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface WordRepository extends JpaRepository<Word,Long> {
    List<Word> findAllByWord(String word);



}
