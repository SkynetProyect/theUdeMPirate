package com.studyup.studyup.repository;

import com.studyup.studyup.model.Author;
import com.studyup.studyup.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {

}
