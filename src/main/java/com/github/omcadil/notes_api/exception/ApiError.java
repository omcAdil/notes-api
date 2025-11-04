package com.github.omcadil.notes_api.exception;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiError {
    private String code;
    private String message;
    private OffsetDateTime timestamp;
}