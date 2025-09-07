package com.astrapay.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.astrapay.entity.Note;

@Repository
public class NoteRepository {
    private final Map<Long, Note> noteStore = new ConcurrentHashMap<>();
    private Long counter = 1L;

    public List<Note> findAll() {
        return new ArrayList<>(noteStore.values());
    }
    public Note save(Note note) {
        note.setId(counter++);
        noteStore.put(note.getId(), note);
        return note;
    }

    public Optional<Note> deleteById(Long id){
        Note removed = noteStore.remove(id);
        return Optional.ofNullable(removed);
    }
}
