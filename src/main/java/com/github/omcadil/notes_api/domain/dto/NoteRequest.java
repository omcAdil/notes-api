package com.github.omcadil.notes_api.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteRequest {
    @NotBlank(message = "Заголовок не может быть пустым")
    private String title;

    @NotBlank(message = "Текст не может быть пустым")
    private String text;
}
