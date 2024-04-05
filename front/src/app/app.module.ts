import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { TopicComponent } from './features/topics/components/topic/topic.component';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent
    // DONE : supprimé d'ici : TopicComponent
  ],
  imports: [
    BrowserModule,
    // DONE : added : 
    HttpClientModule,
    AppRoutingModule,
    // TODO : supprimer 2 lignes si suppression metwo
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
