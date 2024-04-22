import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListComponent } from './components/list/list.component';
import { NewComponent } from './components/new/new.component';
import { DetailComponent } from './components/detail/detail.component';

const routes: Routes = [
  { title: 'Posts', path: '', component: ListComponent },
  { title: 'New', path: 'new', component: NewComponent },
  { title: 'Detail', path: 'detail/:id', component: DetailComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PostsRoutingModule { }
