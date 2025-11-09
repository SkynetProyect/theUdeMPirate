package com.studyup.studyup.service.document;

import com.studyup.studyup.model.Document;

import java.util.List;
import java.util.Optional;

public interface DocumentUsecase {

    List<Document> getAll();
    Optional<Document> getById(Integer id);
    List<Document> getByName(String name);
    List<Document> getByCategory(Integer categoryId);
    List<Document> getByAuthor(Integer authorId);
    void delete(Integer id);
    Document save(Document document);
    Document update(Document document);

}
