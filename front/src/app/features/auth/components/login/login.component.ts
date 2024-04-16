import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth-service';
import { Router } from '@angular/router';
import { LoginRequest } from '../../model/loginRequest.interface';
import { SessionService } from 'src/app/services/session.service';
import { User } from 'src/app/model/user.interface';
import { AuthSuccess } from '../../model/authSuccess.interface';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  public onError = false;

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
  });

  constructor(private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private sessionService: SessionService) {
  }

  public submit(): void {
    const loginRequest = this.form.value as LoginRequest;

    this.authService.login(loginRequest).subscribe(
      (response: AuthSuccess) => {
        localStorage.setItem('token', response.token);
        this.authService.me().subscribe((user: User) => {
          this.sessionService.logIn(user);
          this.router.navigate(['/posts']);
        });
        this.router.navigate(['/posts']);
      },
      error => this.onError = true,
    );
  }

  public back() {
    window.history.back();
  }

}


