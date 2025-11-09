package com.studyup.studyup.service.tag;

import com.studyup.studyup.model.Tag;

import java.util.List;
import java.util.Optional;

public interface TagUsecase {

    List<Tag> getAll();
    Optional<Tag> getById(Integer id);
    void delete(Integer id);
    Tag save(Tag tag);
    Tag update(Tag tag);

}
