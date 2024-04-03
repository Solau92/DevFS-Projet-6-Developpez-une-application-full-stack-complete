import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth-service';
import { Router } from '@angular/router';
import { LoginRequest } from '../../interfaces/loginRequest.interface';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  public onError = false;

  constructor(private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router) {
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

  public submit(): void {
    const loginRequest = this.form.value as LoginRequest;
    this.authService.login(loginRequest).subscribe({
      next: (_: void) => this.router.navigate(['/topics']),
      error: _ => this.onError = true,
    }
    );
  }

}
