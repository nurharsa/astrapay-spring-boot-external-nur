package com.astrapay.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.astrapay.dto.NoteRequestDto;
import com.astrapay.dto.NoteResponseDto;
import com.astrapay.entity.Note;
import com.astrapay.repository.NoteRepository;

public class NoteServiceTest {

    private NoteService noteService;
    private NoteRepository noteRepository;

    @BeforeEach
    void setUp() {
        noteRepository = mock(NoteRepository.class);
        noteService = new NoteService(noteRepository);
    }

    @Test
    void testAddNote_success() {
        Note note = new Note();
        note.setId(1L);
        note.setContent("Hello world");

        when(noteRepository.save(any(Note.class))).thenReturn(note);

        NoteResponseDto response = noteService.addNote(new NoteRequestDto("Title Hello world", "Content Hello world"));

        assertNotNull(response);
        assertEquals("Hello world", response.getContent());
        assertEquals(1L, response.getId());
    }

    @Test
    void testGetAllNotes_paging() {
        Note n1 = new Note(); n1.setId(1L); n1.setTitle("Title First"); n1.setContent("Content First");
        Note n2 = new Note(); n2.setId(2L); n2.setTitle("Title Second"); n2.setContent("Content Second");

        when(noteRepository.findAll()).thenReturn(Arrays.asList(n1, n2));

        // Simulate Pageable with page 0, size 10
        var paged = noteService.getAllNotes(org.springframework.data.domain.PageRequest.of(0, 10));

        assertEquals(2, paged.getContent().size());
        assertEquals("First", paged.getContent().get(0).getTitle());
        assertEquals("Second", paged.getContent().get(1).getTitle());
        assertEquals(1, paged.getTotalPages());
        assertEquals(2, paged.getTotalElements());
    }

    @Test
    void testDeleteNote_success() {
        Note note = new Note();
        note.setId(1L);

        when(noteRepository.deleteById(1L)).thenReturn(Optional.of(note));

        Optional<Note> deleted = noteService.deleteNote(1L);

        assertTrue(deleted.isPresent());
    }

    @Test
    void testDeleteNote_notFound() {
        when(noteRepository.deleteById(999L)).thenReturn(Optional.empty());

        Optional<Note> deleted = noteService.deleteNote(999L);

        assertFalse(deleted.isPresent());
    }
}
