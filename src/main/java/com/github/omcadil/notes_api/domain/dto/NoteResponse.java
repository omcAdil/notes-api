package com.github.omcadil.notes_api.domain.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteResponse {
    private Long id;
    private String title;
    private String text;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
