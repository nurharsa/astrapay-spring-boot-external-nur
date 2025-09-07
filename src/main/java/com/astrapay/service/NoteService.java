package com.astrapay.service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.astrapay.dto.NoteRequestDto;
import com.astrapay.dto.NoteResponseDto;
import com.astrapay.dto.PagedResponseDto;
import com.astrapay.entity.Note;
import com.astrapay.repository.NoteRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    public PagedResponseDto<NoteResponseDto> getAllNotes(Pageable pageable) {
        List<Note> notes = noteRepository.findAll();

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Note> pagedList;

        if(notes.size() < startItem) {
            pagedList = Collections.emptyList();
        }else{
            int toIndex = Math.min(startItem + pageSize, notes.size());
            pagedList = notes.subList(startItem, toIndex);
        }

        List<NoteResponseDto> responseList = pagedList.stream()
                .map(note -> new NoteResponseDto(note.getId(), note.getTitle(), note.getContent(), note.getCreatedDate(), note.getModifiedDate()))
                .collect(Collectors.toList());

        int totalPages = (int) Math.ceil((double) notes.size()/ pageSize);

        return new PagedResponseDto<>(
            currentPage,
            totalPages,
            notes.size(),
            pageSize,
            responseList
        );
    }

    public NoteResponseDto addNote(NoteRequestDto request){
        Note note = new Note();
        note.setContent(request.getContent().trim());
        note.setTitle(request.getTitle().trim());
        
        LocalDateTime now = LocalDateTime.now();
        note.setCreatedDate(now);
        note.setModifiedDate(now);

        Note savedNote = noteRepository.save(note);

        return new NoteResponseDto(
            savedNote.getId(), 
            savedNote.getTitle(), 
            savedNote.getContent(),
            savedNote.getCreatedDate(),
            savedNote.getModifiedDate()
        );
    }

    public NoteResponseDto updateNote(Long id, NoteRequestDto request){
        List<Note> notes = noteRepository.findAll();
        Optional<Note> optionalNote = noteRepository.findAll().stream()
            .filter(n -> n.getId().equals(id))
            .findFirst();

        if(optionalNote.isEmpty()){
            throw new RuntimeException("Note not found");
        }

        Note note = optionalNote.get();
        note.setTitle(request.getTitle().trim());
        note.setContent(request.getContent().trim());
        note.setModifiedDate(LocalDateTime.now());

        return new NoteResponseDto(
            note.getId(),
            note.getTitle(),
            note.getContent(),
            note.getCreatedDate(),
            note.getModifiedDate()
        );
    }

    public Optional<Note> deleteNote(Long id){
        return noteRepository.deleteById(id);
    }

}
