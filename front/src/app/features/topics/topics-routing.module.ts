import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { TopicComponent } from "./components/topic/topic.component";

const routes: Routes = [
    { title: 'Topics', path: '', component: TopicComponent }
  ];
  
  @NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
  })
  export class TopicsRoutingModule {
  }