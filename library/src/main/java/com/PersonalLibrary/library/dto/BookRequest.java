package com.PersonalLibrary.library.dto;

import com.PersonalLibrary.library.model.ReadStatus;

public record BookRequest(String title, Long authorId, ReadStatus status) {
}
