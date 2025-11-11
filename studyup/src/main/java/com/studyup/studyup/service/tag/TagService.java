package com.studyup.studyup.service.tag;

import com.studyup.studyup.model.Tag;
import com.studyup.studyup.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService implements TagUsecase {

    private TagRepository repository;

    public TagService(TagRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Tag> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Tag> getById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Tag save(Tag tag) {
        return repository.save(tag);
    }

    @Override
    public Tag update(Tag tag) {
        return repository.save(tag);
    }
}
