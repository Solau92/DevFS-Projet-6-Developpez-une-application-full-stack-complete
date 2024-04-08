import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PostsRoutingModule } from './posts-routing.module';
import { ListComponent } from './components/list/list.component';
import { NewComponent } from './components/new/new.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DetailComponent } from './components/detail/detail.component';

@NgModule({
  declarations: [
    ListComponent,
    NewComponent,
    DetailComponent
  ],
  imports: [
    CommonModule,
    PostsRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class PostsModule { }
