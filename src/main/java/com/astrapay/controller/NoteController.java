package com.astrapay.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.astrapay.dto.NoteRequestDto;
import com.astrapay.dto.NoteResponseDto;
import com.astrapay.dto.PagedResponseDto;
import com.astrapay.entity.Note;
import com.astrapay.service.NoteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@Api(tags = "Note API")
@RestController
@RequestMapping("/notes")
@AllArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @ApiOperation(value = "Get all notes")
    @GetMapping
    public ResponseEntity<PagedResponseDto<NoteResponseDto>> getNotes(Pageable pageable){
        return ResponseEntity.ok(noteService.getAllNotes(pageable));
    }

    @ApiOperation(value = "Insert Note")
    @PostMapping
    public ResponseEntity<NoteResponseDto> addNote(@Valid @RequestBody NoteRequestDto request){
        NoteResponseDto response = noteService.addNote(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ApiOperation(value = "Update Note")
    @PutMapping("/{id}")
    public ResponseEntity<NoteResponseDto> updateNote(
            @PathVariable Long id,
            @Valid @RequestBody NoteRequestDto request){
        NoteResponseDto response = noteService.updateNote(id, request);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Delete Note by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteNote(@PathVariable Long id){
        Optional<Note> deleted = noteService.deleteNote(id);
        Map<String, Object> response = new HashMap<>();

        if (deleted.isPresent()){
            response.put("deleted", true);
            return ResponseEntity.ok(response);
        } else {
            response.put("deleted", false);
            response.put("message", "Note not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
