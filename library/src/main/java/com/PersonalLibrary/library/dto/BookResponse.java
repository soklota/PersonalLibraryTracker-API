package com.PersonalLibrary.library.dto;

import com.PersonalLibrary.library.model.ReadStatus;

public record BookResponse(Long id, String title, String authorName, ReadStatus status, Integer rating) {
}
