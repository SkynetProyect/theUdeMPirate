package com.studyup.studyup.service.category;

import com.studyup.studyup.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryUsecase {

    List<Category> getAll();
    Optional<Category> getById(Integer id);
    void delete(Integer id);
    Category save(Category category);
    Category update(Category category);

}
