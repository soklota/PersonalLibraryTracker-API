package com.PersonalLibrary.library.service;

import com.PersonalLibrary.library.dto.AuthorRequest;
import com.PersonalLibrary.library.dto.AuthorResponse;
import com.PersonalLibrary.library.exception.ResourceNotFoundException;
import com.PersonalLibrary.library.model.Author;
import com.PersonalLibrary.library.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    public AuthorResponse addAuthor(AuthorRequest request){
        Author author = new Author();
        author.setName(request.name());

        Author saved = authorRepository.save(author);
        return toResponse(saved);
    }

    public List<AuthorResponse> getAuthors() {
        return authorRepository.findAll().stream().map(this::toResponse).toList();
    }

    public AuthorResponse getAuthor(Long id){
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id " + id));
        return toResponse(author);
    }

    private AuthorResponse toResponse(Author author){
        return new AuthorResponse(
                author.getId(),
                author.getName(),
                author.getBooks().size()
        );
    }
}
