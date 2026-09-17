package com.PersonalLibrary.library.service;

import com.PersonalLibrary.library.dto.BookRequest;
import com.PersonalLibrary.library.dto.BookResponse;
import com.PersonalLibrary.library.exception.ResourceNotFoundException;
import com.PersonalLibrary.library.model.Author;
import com.PersonalLibrary.library.model.Book;
import com.PersonalLibrary.library.model.ReadStatus;
import com.PersonalLibrary.library.repository.AuthorRepository;
import com.PersonalLibrary.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service //mark as Spring-managed bean
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository){
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public BookResponse addBook(BookRequest request){
        Author author = authorRepository.findById(request.authorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id "+request.authorId()));
        Book book = new Book();
        book.setTitle(request.title());
        book.setAuthor(author);
        book.setStatus(request.status());

        Book saved = bookRepository.save(book);
        return toResponse(saved);
    }

    public BookResponse markFinished(Long bookId, Integer rating){
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id "+bookId));
        book.setStatus(ReadStatus.FINISHED);
        book.setRating(rating);
        book.setDateFinished(LocalDate.now());

        Book saved = bookRepository.save(book);
        return toResponse(saved);
    }

    public List<BookResponse> getBooks(ReadStatus status){
        List<Book> books = (status == null)
                ? bookRepository.findAll()
                : bookRepository.findByStatus(status);

        return books.stream().map(this::toResponse).toList();
    }

    private BookResponse toResponse(Book book){
        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor().getName(),
                book.getStatus(),
                book.getRating()
        );
    }
}
