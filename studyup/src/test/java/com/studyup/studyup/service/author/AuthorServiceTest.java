package com.studyup.studyup.service.author;

import com.studyup.studyup.model.Author;
import com.studyup.studyup.repository.AuthorRepository;
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
class AuthorServiceTest {

    @Mock
    private AuthorRepository repository;

    @InjectMocks
    private AuthorService service;

    @Test
    void getAll() {
        when(repository.findAll()).thenReturn(List.of(new Author()));

        List<Author> objeto = service.getAll();

        assertFalse(objeto.isEmpty());
        verify(repository).findAll();
    }

    // ---------------------------------------------------------
    @Test
    void getById() {
        Author objeto = new Author();
        objeto.setId(1);

        when(repository.findById(1)).thenReturn(Optional.of(objeto));
        Author result = service.getById(1).orElse(new Author(3,""));
        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(repository).findById(1);
    }


    @Test
    void save_returnsSavedAuthor() {
        // Arrange
        Author objeto = new Author();
        objeto.setAuthor("Julio Cortázar");

        when(repository.save(any(Author.class))).thenAnswer(invocation -> {
            Author a = invocation.getArgument(0);
            a.setId(1);
            return a;
        });

        // Act
        Author result = service.save(objeto);

        // Assert
        assertNotNull(result.getId());
        assertEquals("Julio Cortázar", result.getAuthor());
        verify(repository).save(objeto);
    }

    @Test
    void delete() {
        service.delete(1);
        verify(repository).deleteById(1);
    }


}