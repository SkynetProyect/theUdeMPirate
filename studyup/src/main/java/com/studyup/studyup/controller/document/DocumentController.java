package com.studyup.studyup.controller.document;

import com.studyup.studyup.model.Document;
import com.studyup.studyup.service.document.DocumentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/api/document")
public class DocumentController {

    private final DocumentService service;

    public DocumentController(DocumentService service){
        this.service = service;
    }
    
    @GetMapping
    public ResponseEntity<List<Document>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // 🔸 GET - obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<DocumentDTO> getById(@PathVariable Integer id) {
        return service.getById(id)
                .map(document -> ResponseEntity.ok(this.toDTO(document)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/byAuthor/{id}")
    public ResponseEntity<List<Document>> getByAuthor(@PathVariable Integer id){ return ResponseEntity.ok(service.getByAuthor(id));}


    @GetMapping("/byCategory/{id}")
    public ResponseEntity<List<Document>> getByCategory(@PathVariable Integer id){ return ResponseEntity.ok(service.getByCategory(id));}


    @GetMapping("/byName/{nombre}")
    public ResponseEntity<List<Document>> getByName(@PathVariable String name){
        return ResponseEntity.ok(service.getByName(name));
    }


    @PostMapping
    public ResponseEntity<Document> create(@RequestBody DocumentDTO value) {
        Document document = new Document();
        document.setTittle(value.getTittle());
        document.setAuthorId(value.getAuthorId());
        document.setDescription(value.getDescription());
        
        // Convertir Base64 a byte[]
        if (value.getDocument() != null && !value.getDocument().isEmpty()) {
            try {
                byte[] decodedBytes = Base64.getDecoder().decode(value.getDocument());
                document.setDocument(decodedBytes);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Error al decodificar el documento Base64", e);
            }
        }

        document = service.save(document);
        service.setCategoryToDoc(value.getCategoryId(),document.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(document);
    }

    // 🔸 PUT - actualizar usuario
    @PutMapping("/{id}")
    public ResponseEntity<Document> update(@RequestBody Document value) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.update(value));
    }

    // 🔸 DELETE - eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    
    public DocumentDTO toDTO(Document document) {
        DocumentDTO dto = new DocumentDTO();
        dto.setId(document.getId());
        dto.setTittle(document.getTittle());
        dto.setAuthorId(document.getAuthorId());
        dto.setDescription(document.getDescription());
        
        // Convertir byte[] a Base64 para enviar al frontend
        if (document.getDocument() != null) {
            String base64 = Base64.getEncoder().encodeToString(document.getDocument());
            dto.setDocument(base64);
        }
        
        return dto;
    }
}
