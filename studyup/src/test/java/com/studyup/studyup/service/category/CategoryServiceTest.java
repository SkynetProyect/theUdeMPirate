package com.studyup.studyup.service.category;

import com.studyup.studyup.model.Category;
import com.studyup.studyup.repository.CategoryRepository;
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
class CategoryServiceTest {

    @Mock
    private CategoryRepository repository;

    @InjectMocks
    private CategoryService service;

    @Test
    void getAll() {
        when(repository.findAll()).thenReturn(List.of(new Category()));

        List<Category> objeto = service.getAll();

        assertFalse(objeto.isEmpty());
        verify(repository).findAll();
    }

    // ---------------------------------------------------------
    @Test
    void getById() {
        Category objeto = new Category();
        objeto.setId(1);

        when(repository.findById(1)).thenReturn(Optional.of(objeto));
        Category result = service.getById(1).orElse(new Category(3,""));
        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(repository).findById(1);
    }


    @Test
    void save_returnsSavedCategory() {
        // Arrange
        Category objeto = new Category();
        objeto.setNombre("Julio Cortázar");

        when(repository.save(any(Category.class))).thenAnswer(invocation -> {
            Category a = invocation.getArgument(0);
            a.setId(1);
            return a;
        });

        // Act
        Category result = service.save(objeto);

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