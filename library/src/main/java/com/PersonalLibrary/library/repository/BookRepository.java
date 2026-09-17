package com.PersonalLibrary.library.repository;

import com.PersonalLibrary.library.model.Book;
import com.PersonalLibrary.library.model.ReadStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//JpaRepo is a generic interface springdata provides
public interface BookRepository extends JpaRepository<Book, Long> {
    //Book-entity type this repo manages Long-type of that entity's @Id field

    //Spring uses below lines to build and execute sql query and maps each row into Book object
    List<Book> findByStatus(ReadStatus status);
    List<Book> findByAuthor(Long authorId);
}
