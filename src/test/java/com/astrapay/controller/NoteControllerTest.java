package com.astrapay.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Optional;

import com.astrapay.dto.NoteRequestDto;
import com.astrapay.dto.NoteResponseDto;
import com.astrapay.dto.PagedResponse;
import com.astrapay.entity.Note;
import com.astrapay.service.NoteService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(NoteController.class)
public class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteService noteService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddNote_success() throws Exception {
        NoteRequestDto request = new NoteRequestDto("My Title Note", "My Note");
        when(noteService.addNote(any())).thenReturn(new NoteResponseDto(1L, "My Title Note", "My Note", java.time.LocalDateTime.now(), java.time.LocalDateTime.now()));

        mockMvc.perform(post("/notes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.content").value("My Note"));
    }

    @Test
    void testGetAllNotes_success() throws Exception {
        PagedResponse<NoteResponseDto> pagedResponse = new PagedResponse<>(
                0, // currentPage
                1, // totalPages
                2, // totalElements
                10, // pageSize
                Arrays.asList(
                        new NoteResponseDto(1L, "Title First", "Content First", java.time.LocalDateTime.of(2024, 6, 10, 10, 0), java.time.LocalDateTime.of(2024, 6, 10, 10, 0)),
                        new NoteResponseDto(2L, "Title Second", "Content Second", java.time.LocalDateTime.of(2024, 6, 10, 11, 0), java.time.LocalDateTime.of(2024, 6, 10, 10, 0)
                )
        ));

        when(noteService.getAllNotes(any())).thenReturn(pagedResponse);

        mockMvc.perform(get("/notes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].content").value("First"))
                .andExpect(jsonPath("$.content[1].content").value("Second"))
                .andExpect(jsonPath("$.totalElements").value(2));
    }

    @Test
    void testDeleteNote_success() throws Exception {
        when(noteService.deleteNote(1L)).thenReturn(Optional.of(new Note()));

        mockMvc.perform(delete("/notes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.deleted").value(true));
    }

    @Test
    void testDeleteNote_notFound() throws Exception {
        when(noteService.deleteNote(999L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/notes/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.deleted").value(false))
                .andExpect(jsonPath("$.message").value("Note not found"));
    }
}
