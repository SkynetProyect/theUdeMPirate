package com.studyup.studyup.service.category;

import com.studyup.studyup.model.Category;
import com.studyup.studyup.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements CategoryUsecase {

    private CategoryRepository repository;

    public CategoryService(CategoryRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Category> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Category> getById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Category save(Category category) {
        return repository.save(category);
    }

    @Override
    public Category update(Category category) {
        return repository.save(category);
    }
}
