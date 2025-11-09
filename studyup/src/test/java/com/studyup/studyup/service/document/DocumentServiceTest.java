package com.studyup.studyup.service.document;

import com.studyup.studyup.model.Document;
import com.studyup.studyup.repository.DocumentRepository;

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
class DocumentServiceTest {

    @Mock
    private DocumentRepository repository;

    @InjectMocks
    private DocumentService service;

    @Test
    void getAll() {
        when(repository.findAll()).thenReturn(List.of(new Document()));

        List<Document> objeto = service.getAll();

        assertFalse(objeto.isEmpty());
        verify(repository).findAll();
    }

    // ---------------------------------------------------------
    @Test
    void getById() {
        Document objeto = new Document();
        objeto.setId(1);

        when(repository.findById(1)).thenReturn(Optional.of(objeto));
        Document result = service.getById(1).orElse(new Document(3,"","",new byte[0],2, null));
        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(repository).findById(1);
    }


    @Test
    void save_returnsSavedDocument() {
        // Arrange
        Document objeto = new Document();
        objeto.setTittle("Julio Cortázar");

        when(repository.save(any(Document.class))).thenAnswer(invocation -> {
            Document a = invocation.getArgument(0);
            a.setId(1);
            return a;
        });

        // Act
        Document result = service.save(objeto);

        // Assert
        assertNotNull(result.getId());
        assertEquals("Julio Cortázar", result.getTittle());
        verify(repository).save(objeto);
    }

    @Test
    void delete() {
        service.delete(1);
        verify(repository).deleteById(1);
    }


}