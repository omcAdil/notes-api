package com.github.omcadil.notes_api.repository;

import com.github.omcadil.notes_api.domain.entity.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    @Query("select n from Note n " +
            "where (:q is null or lower(n.title) like lower(concat('%', :q, '%')) " +
            " or lower(n.text) like lower(concat('%', :q, '%'))) ")
    Page<Note> search(@Param("q") String q, Pageable pageable);
}

