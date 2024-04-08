import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListComponent } from './components/list/list.component';
import { NewComponent } from './components/new/new.component';
import { DetailComponent } from './components/detail/detail.component';

const routes: Routes = [
  { path: '', title: 'Posts', component: ListComponent },
  { path: 'new', title: 'New', component: NewComponent },
  { path: 'detail/:id', title: 'Detail', component: DetailComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PostsRoutingModule { }
