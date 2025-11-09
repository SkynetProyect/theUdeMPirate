package com.studyup.studyup.service.author;

import com.studyup.studyup.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorUsecase {

    List<Author> getAll();
    Optional<Author> getById(Integer id);
    void delete(Integer id);
    Author save(Author author);
    Author update(Author author);

}
