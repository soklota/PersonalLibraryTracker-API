package com.PersonalLibrary.library.dto;

import jakarta.validation.constraints.NotBlank;
//A Java record (since Java 16) is a compact way to declare an immutable data carrier
public record AuthorRequest(@NotBlank String name) { }
