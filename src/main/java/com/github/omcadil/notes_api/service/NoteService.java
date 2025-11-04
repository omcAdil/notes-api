package com.github.omcadil.notes_api.service;

import com.github.omcadil.notes_api.domain.dto.NoteRequest;
import com.github.omcadil.notes_api.domain.dto.NoteResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoteService {

    NoteResponse create(NoteRequest req);

    Page<NoteResponse> getAll(String query, Pageable pageable);

    NoteResponse getById(Long id);

    NoteResponse update(Long id, NoteRequest req);

    void delete(Long id);

}
