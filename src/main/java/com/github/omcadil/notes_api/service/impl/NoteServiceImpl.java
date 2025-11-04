package com.github.omcadil.notes_api.service.impl;

import com.github.omcadil.notes_api.domain.dto.NoteRequest;
import com.github.omcadil.notes_api.domain.dto.NoteResponse;
import com.github.omcadil.notes_api.domain.entity.Note;
import com.github.omcadil.notes_api.exception.ResourceNotFoundException;
import com.github.omcadil.notes_api.repository.NoteRepository;
import com.github.omcadil.notes_api.service.NoteMapper;
import com.github.omcadil.notes_api.service.NoteService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    @Override
    @Transactional
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

    @Override
    public Page<NoteResponse> getAll(String query, Pageable pageable) {
        return noteRepository.search(query, pageable)
                .map(NoteMapper::toDto);
    }

    @Override
    public NoteResponse getById(Long id) {
        return NoteMapper.toDto(find(id));
    }

    @Override
    @Transactional
    public NoteResponse update(Long id, NoteRequest req) {
        Note n = find(id);
        n.setTitle(req.getTitle());
        n.setText(req.getText());
        n.setUpdatedAt(OffsetDateTime.now());
        return NoteMapper.toDto(noteRepository.save(n));
    }

    @Override
    @Transactional
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
