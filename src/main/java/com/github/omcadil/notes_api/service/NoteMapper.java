package com.github.omcadil.notes_api.service;

import com.github.omcadil.notes_api.domain.dto.NoteResponse;
import com.github.omcadil.notes_api.domain.entity.Note;

public class NoteMapper {
    public static NoteResponse toDto(Note n) {
        return NoteResponse.builder()
                .id(n.getId())
                .title(n.getTitle())
                .text(n.getText())
                .createdAt(n.getCreatedAt())
                .updatedAt(n.getUpdatedAt())
                .build();
    }
}