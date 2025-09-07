import { Component, OnInit } from "@angular/core";
import { Note, NoteService } from "../services/note.service";

@Component({
    selector: "app-home",
    templateUrl: "./home.component.html",
    styleUrls: ["./home.component.css"]
})
export class HomeComponent implements OnInit {
    notes: Note[] = [];
    newTitle: string = "";
    newContent: string = "";

    titleError: string = '';
    contentError: string = '';

    showDeleteModal: boolean = false;
    noteToDelete: Note | null = null;

    currentPage: number = 0;
    totalPages: number = 0;
    pageSize: number = 5;

    constructor(private noteService: NoteService) {}

    ngOnInit() {
        this.loadNotes(this.currentPage);
    }

    loadNotes(page: number) {
        this.noteService.getNotes(page, this.pageSize).subscribe(
            (res: any) => {
                this.notes = res.content;
                this.currentPage = res.currentPage;
                this.totalPages = res.totalPages;
            },
            err => console.error(err)
        );
    }

    validateTitle() {
        this.titleError = this.newTitle.trim() ? '' : '*Title cannot be empty';
    }

    validateContent() {
        this.contentError = this.newContent.trim() ? '' : '*Content cannot be empty';
    }

    showSuccess: boolean = false;

    addNote() {
    this.validateTitle();
    this.validateContent();
    if (this.titleError || this.contentError) return;

    const note: Note = { title: this.newTitle, content: this.newContent };

    this.noteService.addNote(note).subscribe(
        (createdNote: Note) => {
        this.newTitle = "";
        this.newContent = "";
        this.loadNotes(0);

        this.showSuccess = true;
        setTimeout(() => this.showSuccess = false, 3000);
        },
        err => console.error(err)
    );
    }

    deleteNote(note: Note) {
        this.noteToDelete = note;
        this.showDeleteModal = true;
    }

    cancelDelete() {
        this.noteToDelete = null;
        this.showDeleteModal = false;
    }

    confirmDelete() {
        if(!this.noteToDelete || !this.noteToDelete.id) return;

        this.noteService.deleteNote(this.noteToDelete.id).subscribe(
            () => {
                this.loadNotes(this.currentPage);
                this.noteToDelete = null;
                this.showDeleteModal = false;
            },
            err => console.error(err)
        );
    }


    prevPage(){
        if(this.currentPage > 0) this.loadNotes(this.currentPage -1);
    }

    nextPage(){
        if(this.currentPage < this.totalPages -1) this.loadNotes(this.currentPage +1);
    }
}
