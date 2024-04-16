import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RegisterRequest } from '../../model/registerRequest.interface';
import { AuthService } from '../../services/auth-service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  public onError = false;

  private passwordRegx: RegExp = /^(?=[^A-Z]*[A-Z])(?=[^a-z]*[a-z])(?=.*[@$!%*#?&^_-])(?=\D*\d).{8,}$/;

  constructor(private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router) {
  }

  public form = this.formBuilder.group({

    username: [
      '',
      [
        Validators.required,
        Validators.min(6),
        Validators.max(20)
      ]
    ]
    ,
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
        Validators.min(8),
        Validators.pattern(this.passwordRegx)
      ]
    ]
  })

  public submit(): void {
    const registerRequest = this.form.value as RegisterRequest;
    this.authService.register(registerRequest).subscribe({
      next: (_: void) => this.router.navigate(['/auth/login']), 
      error: _ => this.onError = true,
    }
    );
  }

  public back() {
    window.history.back();
  }
  
}
