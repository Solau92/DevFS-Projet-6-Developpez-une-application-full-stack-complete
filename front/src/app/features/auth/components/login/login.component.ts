import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth-service';
import { Router } from '@angular/router';
import { LoginRequest } from '../../interfaces/loginRequest.interface';
import { SessionInformation } from 'src/app/interfaces/session-information.interface';
import { SessionService } from 'src/app/services/session.service';
import { User } from 'src/app/interfaces/user.interface';
import { AuthSuccess } from '../../interfaces/authSuccess.interface';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  public onError = false;

  constructor(private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private sessionService: SessionService) {
  }

  public form = this.formBuilder.group({
    email: [
      '',
      [
        Validators.required,
        Validators.email
      ]
    ],
    password: [
      '',
      [
        Validators.required,
        Validators.min(8)
      ]
    ]
  })

  // public submit(): void {
  //   const loginRequest = this.form.value as LoginRequest;
  //   this.authService.login(loginRequest).subscribe({
  //     next: (_: void) => this.router.navigate(['/topics']),
  //     error: _ => this.onError = true,
  //   }
  //   );
  // }

  // public submit(): void {
  //   const loginRequest = this.form.value as LoginRequest;
  //   this.authService.login(loginRequest).subscribe({
  //     next: (sessionInformation: SessionInformation) => {
  //       this.sessionService.logIn(sessionInformation);
  //       this.router.navigate(['/topics']);
  //     },
  //     error: error => this.onError = true,
  //   });
  // }

  // Version * 
  // public submit(): void {
  //   const loginRequest = this.form.value as LoginRequest;

  //   this.authService.login(loginRequest).subscribe({
  //     next: (sessionInformation: SessionInformation) => {
  //       this.sessionService.logIn(sessionInformation);
  //       this.router.navigate(['/topics']);
  //     },
  //     error: error => this.onError = true,
  //   });
  // }

  // Version ***
  public submit(): void {
    const loginRequest = this.form.value as LoginRequest;

    this.authService.login(loginRequest).subscribe(
      (response: AuthSuccess) => {
        localStorage.setItem('token', response.token);
        this.authService.me().subscribe((user: User) => {
          this.sessionService.logIn(user);
          this.router.navigate(['/topics']);
        });
        this.router.navigate(['/topics']);
      },
      error => this.onError = true,
    );
  }

}


