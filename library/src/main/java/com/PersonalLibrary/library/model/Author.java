package com.PersonalLibrary.library.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Author {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //Tells the database to auto-generate the ID value
    private Long id; //primary key of author table
    private String name; //another column in table

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>(); //initialized to empty array - will be list of Book objects
}
