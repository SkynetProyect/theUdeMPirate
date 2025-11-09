package com.studyup.studyup.service.tag;

import com.studyup.studyup.model.Tag;
import com.studyup.studyup.repository.TagRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TagServiceTest {

    @Mock
    private TagRepository repository;

    @InjectMocks
    private TagService service;

    @Test
    void getAll() {
        when(repository.findAll()).thenReturn(List.of(new Tag()));

        List<Tag> objeto = service.getAll();

        assertFalse(objeto.isEmpty());
        verify(repository).findAll();
    }

    // ---------------------------------------------------------
    @Test
    void getById() {
        Tag objeto = new Tag();
        objeto.setId(1);

        when(repository.findById(1)).thenReturn(Optional.of(objeto));
        Tag result = service.getById(1).orElse(new Tag(3,""));
        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(repository).findById(1);
    }


    @Test
    void save_returnsSavedTag() {
        // Arrange
        Tag objeto = new Tag();
        objeto.setNombre("Julio Cortázar");

        when(repository.save(any(Tag.class))).thenAnswer(invocation -> {
            Tag a = invocation.getArgument(0);
            a.setId(1);
            return a;
        });

        // Act
        Tag result = service.save(objeto);

        // Assert
        assertNotNull(result.getId());
        assertEquals("Julio Cortázar", result.getNombre());
        verify(repository).save(objeto);
    }

    @Test
    void delete() {
        service.delete(1);
        verify(repository).deleteById(1);
    }


}