import { Injectable } from "@angular/core";
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { Observable } from "rxjs/Observable";
import 'rxjs/add/operator/map';

export interface Note {
    id?: number;
    content: string;
    title?: string;
    createdAt?: string;
    // modifiedAt?: string;
}

@Injectable()
export class NoteService {
    private baseUrl = 'http://localhost:8000/notes';

    constructor(private http: Http) {}

    getNotes(page: number = 0, size: number): Observable<any> {
        return this.http.get(`${this.baseUrl}?page=${page}&size=${size}`)
            .map(res => res.json());
    }

    addNote(note: Note): Observable<Note> {
        const headers = new Headers({ 'Content-Type': 'application/json' });
        const options = new RequestOptions({ headers: headers });
        return this.http.post(this.baseUrl, JSON.stringify(note), options)
            .map((res: Response) => res.json());
    }

    deleteNote(id: number): Observable<any> {
        return this.http.delete(`${this.baseUrl}/${id}`)
            .map((res: Response) => res.json());
    }
}