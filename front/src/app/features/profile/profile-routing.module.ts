import { RouterModule, Routes } from "@angular/router";
import { ProfileComponent } from "./components/profile/profile.component";
import { NgModule } from "@angular/core";

const routes: Routes = [
    { title: 'Profile', path: '', component: ProfileComponent }
  ];
  
  @NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
  })
  export class ProfileRoutingModule {
  }