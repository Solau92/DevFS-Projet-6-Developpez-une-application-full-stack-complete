import { RouterModule, Routes } from "@angular/router";
import { MeComponent } from "./components/me/me.component";
import { NgModule } from "@angular/core";

const routes: Routes = [
    { path: '', title: 'Profile', component: MeComponent }
  ];
  
  @NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
  })
  export class ProfileRoutingModule {
  }