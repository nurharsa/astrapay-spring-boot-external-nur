import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { ApiComponent } from './api/api.component';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { NoteService } from './services/note.service';
import { HomeComponent } from './home/home.component';
import { TruncateCharsPipe } from './pipes/truncate-words.pipe';

@NgModule({
  declarations: [
    AppComponent,
    ApiComponent,
    HomeComponent,
    TruncateCharsPipe
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule
  ],
  providers: [NoteService],
  bootstrap: [AppComponent]
})
export class AppModule { }
