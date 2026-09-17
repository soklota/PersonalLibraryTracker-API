package com.PersonalLibrary.library.controller;


import com.PersonalLibrary.library.dto.BookRequest;
import com.PersonalLibrary.library.dto.BookResponse;
import com.PersonalLibrary.library.model.ReadStatus;
import com.PersonalLibrary.library.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Marks as a Spring MVC controller
@RequestMapping("/api/books") // sets a base path every endpoint in this class
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    //create book endpoint
    @PostMapping
    public ResponseEntity<BookResponse> addBook(@Valid @RequestBody BookRequest request){
        return ResponseEntity.status(201).body(bookService.addBook(request));
    }

    //finish book endpoint
    @PostMapping("/{id}/finish")
    public BookResponse finishBook(@PathVariable Long id, @RequestParam Integer rating){ //param and not body because rating is simple value
        return bookService.markFinished(id, rating);
    }

    //list/filter endpoint
    @GetMapping
    public List<BookResponse> getBooks(@RequestParam(required = false) ReadStatus status){
        return bookService.getBooks(status);
    }
}
