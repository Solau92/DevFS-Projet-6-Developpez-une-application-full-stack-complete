import { NgModule } from "@angular/core";
import { MeComponent } from "./components/me/me.component";
import { CommonModule } from "@angular/common";
import { ProfileRoutingModule } from "./profile-routing.module";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";

const materialModules: never[] = [
];

@NgModule({
  declarations: [
    MeComponent
  ],
  imports: [
    CommonModule,
    ProfileRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class ProfileModule { }