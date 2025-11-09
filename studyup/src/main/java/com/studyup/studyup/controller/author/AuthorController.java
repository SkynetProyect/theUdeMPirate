package com.studyup.studyup.controller.author;

import com.studyup.studyup.model.Author;
import com.studyup.studyup.service.author.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/author")
public class AuthorController {

    private final AuthorService service;

    public AuthorController(AuthorService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Author>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // 🔸 GET - obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Author> getById(@PathVariable Integer id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔸 POST - crear usuario
    @PostMapping
    public ResponseEntity<Author> create(@RequestBody Author value) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(value));
    }

    // 🔸 PUT - actualizar usuario
    @PutMapping("/{id}")
    public ResponseEntity<Author> update(@RequestBody Author value) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.update(value));
    }

    // 🔸 DELETE - eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
