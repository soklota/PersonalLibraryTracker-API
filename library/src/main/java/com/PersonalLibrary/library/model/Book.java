package com.PersonalLibrary.library.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @NotBlank //Bean Validation annotation - when this object is validated, title must not be null, empty, or whitespace
    private String title;
    @Enumerated(EnumType.STRING) //tells JPA to store the enum as its name in database column
    private ReadStatus status;

    @Min(1) @Max(5) //constrains the value to 1–5 when validated
    private Integer rating; //Integer not int so it can be null
    private LocalDate dateFinished;

    @ManyToOne //many books to one author
    @JoinColumn(name = "author_id") //where the foreign key column lives in database
    private Author author;

}
