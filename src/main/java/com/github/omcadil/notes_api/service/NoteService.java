package com.github.omcadil.notes_api.service;

import com.github.omcadil.notes_api.domain.dto.NoteRequest;
import com.github.omcadil.notes_api.domain.dto.NoteResponse;
import com.github.omcadil.notes_api.domain.entity.Note;
import com.github.omcadil.notes_api.exception.ResourceNotFoundException;
import com.github.omcadil.notes_api.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    public NoteResponse create(NoteRequest req) {
        var now = OffsetDateTime.now();
        Note n = Note.builder()
                .title(req.getTitle())
                .text(req.getText())
                .createdAt(now)
                .updatedAt(now)
                .build();
        return NoteMapper.toDto(noteRepository.save(n));
    }

    public Page<NoteResponse> getAll(String query, Pageable pageable) {
        return noteRepository.search(query, pageable)
                .map(NoteMapper::toDto);
    }

    public NoteResponse getById(Long id) {
        return NoteMapper.toDto(find(id));
    }

    public NoteResponse update(Long id, NoteRequest req) {
        Note n = find(id);
        n.setTitle(req.getTitle());
        n.setText(req.getText());
        n.setUpdatedAt(OffsetDateTime.now());
        return NoteMapper.toDto(noteRepository.save(n));
    }

    public void delete(Long id) {
        if (!noteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Note not found: id=" + id);
        }
        noteRepository.deleteById(id);
    }

    private Note find(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note not found: id=" + id));
    }
}
