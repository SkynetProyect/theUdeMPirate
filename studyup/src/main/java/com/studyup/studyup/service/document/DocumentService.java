package com.studyup.studyup.service.document;

import com.studyup.studyup.model.Document;
import com.studyup.studyup.repository.DocumentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentService implements DocumentUsecase {

    private DocumentRepository repository;

    public DocumentService(DocumentRepository repository){
        this.repository = repository;
    }

    public void setCategoryToDoc(Integer idCategory, Integer id){
        repository.setCategoryToDoc(idCategory, id);
    }

    @Override
    public List<Document> getAll() {
        return repository.findAll();
    }



    @Override
    public Optional<Document> getById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Document> getByName(String name) {
        return repository.findAll().stream().filter( doc -> doc.getTittle().equals(name)).toList();
    }

    @Override
    public List<Document> getByCategory(Integer categoryId) {
        return repository.getByCategory(categoryId);
    }


    @Override
    public List<Document> getByAuthor(Integer authorId) {
        return repository.findAll().stream().filter(doc -> doc.getAuthorId().equals(authorId)).toList();
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Document save(Document document) {
        return repository.save(document);
    }

    @Override
    public Document update(Document document) {
        return repository.save(document);
    }
}
