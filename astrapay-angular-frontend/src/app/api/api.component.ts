import { Component, OnInit } from "@angular/core";
import { Note, NoteService } from "../services/note.service";

@Component({
    selector: "app-api",
    templateUrl: "./api.component.html",
})
export class ApiComponent implements OnInit{
    notes: Note[] = [];
    newNoteContent: string = "";

    constructor(private noteService: NoteService) {}

    ngOnInit(){
        this.loadNotes();
    }
    loadNotes(){
        const page = 0;
        const size = 5; 

        this.noteService.getNotes(page, size).subscribe(
            (res: any) => {
                this.notes = res.content;
            },
            err => console.error(err)
        );
    }

    addNote(){
        if(!this.newNoteContent.trim()) return;{
            const note: Note = { content: this.newNoteContent };
            this.noteService.addNote(note).subscribe(
                added => {
                    this.notes.push(added);
                    this.newNoteContent = '';
                },
                err => console.error(err)
            );
        }
    }

    deleteNote(id: number){
        this.noteService.deleteNote(id).subscribe(
            res => this.notes = this.notes.filter(n => n.id !== id),
            err => console.error(err)
        );
    }
}