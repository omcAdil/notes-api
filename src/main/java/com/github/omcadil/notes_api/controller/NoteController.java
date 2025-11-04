package com.github.omcadil.notes_api.controller;

import com.github.omcadil.notes_api.domain.dto.NoteRequest;
import com.github.omcadil.notes_api.domain.dto.NoteResponse;
import com.github.omcadil.notes_api.service.impl.NoteServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteServiceImpl noteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NoteResponse create(@Valid @RequestBody NoteRequest req) {
        return noteService.create(req);
    }

    @GetMapping
    public Page<NoteResponse> list(@RequestParam(required = false) String query, Pageable pageable) {
        return noteService.getAll(query, pageable);
    }

    @GetMapping("/{id}")
    public NoteResponse get(@PathVariable Long id) {
        return noteService.getById(id);
    }

    @PutMapping("/{id}")
    public NoteResponse update(@PathVariable Long id, @Valid @RequestBody NoteRequest req) {
        return noteService.update(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        noteService.delete(id);
    }
}
