package com.studyup.studyup.service.author;

import com.studyup.studyup.model.Author;
import com.studyup.studyup.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService implements AuthorUsecase{

    private AuthorRepository repository;

    public AuthorService(AuthorRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Author> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Author> getById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Author save(Author author) {
        return repository.save(author);
    }

    @Override
    public Author update(Author author) {
        return repository.save(author);
    }
}
