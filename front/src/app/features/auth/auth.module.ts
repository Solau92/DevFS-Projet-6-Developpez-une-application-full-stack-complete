import { CommonModule } from "@angular/common";
import { AuthRoutingModule } from "./auth-routing.module";
import { RegisterComponent } from "./components/register/register.component";
import { NgModule } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './components/login/login.component';

const materialModules: never[] = [
];

@NgModule({
  declarations: [
    RegisterComponent,
    LoginComponent
  ],
  imports: [
    CommonModule,
    AuthRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class AuthModule { }