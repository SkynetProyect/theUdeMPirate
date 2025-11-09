package com.studyup.studyup.controller.category;

import com.studyup.studyup.model.Category;

import com.studyup.studyup.service.category.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service){
        this.service = service;
    }
    
    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // 🔸 GET - obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable Integer id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔸 POST - crear usuario
    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category value) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(value));
    }

    // 🔸 PUT - actualizar usuario
    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@RequestBody Category value) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.update(value));
    }

    // 🔸 DELETE - eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
