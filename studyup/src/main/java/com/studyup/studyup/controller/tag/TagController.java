package com.studyup.studyup.controller.tag;

import com.studyup.studyup.model.Tag;
import com.studyup.studyup.service.tag.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
public class TagController {

    private final TagService service;

    public TagController(TagService service){
        this.service = service;
    }
    
    @GetMapping
    public ResponseEntity<List<Tag>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // 🔸 GET - obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Tag> getById(@PathVariable Integer id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔸 POST - crear usuario
    @PostMapping
    public ResponseEntity<Tag> create(@RequestBody Tag value) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(value));
    }

    // 🔸 PUT - actualizar usuario
    @PutMapping("/{id}")
    public ResponseEntity<Tag> update(@RequestBody Tag value) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.update(value));
    }

    // 🔸 DELETE - eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
