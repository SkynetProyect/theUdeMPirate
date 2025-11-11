package com.studyup.studyup.repository;

import com.studyup.studyup.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag,Integer> {

}
