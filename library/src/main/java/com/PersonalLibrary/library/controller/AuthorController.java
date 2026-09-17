package com.PersonalLibrary.library.controller;

import com.PersonalLibrary.library.dto.AuthorRequest;
import com.PersonalLibrary.library.dto.AuthorResponse;
import com.PersonalLibrary.library.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<AuthorResponse> addAuthor(@Valid @RequestBody AuthorRequest request){
        return ResponseEntity.status(201).body(authorService.addAuthor(request));
    }

    @GetMapping
    public List<AuthorResponse> getAuthors() {
        return authorService.getAuthors();
    }

    @GetMapping("/{id}")
    public AuthorResponse getAuthor(@PathVariable Long id){
        return authorService.getAuthor(id);
    }

}
