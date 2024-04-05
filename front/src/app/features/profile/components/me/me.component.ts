import { Component, DoCheck, OnChanges, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/features/auth/services/auth-service';
import { User } from 'src/app/interfaces/user.interface';
import { SessionService } from 'src/app/services/session.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-me',
  templateUrl: './me.component.html',
  styleUrls: ['./me.component.css']
})
export class MeComponent implements OnInit {

  public onError = false;

  private passwordRegx: RegExp = /^(?=[^A-Z]*[A-Z])(?=[^a-z]*[a-z])(?=.*[@$!%*#?&^_-])(?=\D*\d).{8,}$/;

  public formMod: FormGroup | undefined;

  public user: User | undefined;

  constructor(private formBuilder: FormBuilder,
    private router: Router,
    private userService: UserService,
    private sessionService: SessionService,
    private authService: AuthService
  ) {
  }

  public form = this.formBuilder.group({

    username: ['',
      [
        Validators.required,
        Validators.min(6),
        Validators.max(20)
      ]
    ]
    ,
    email: ['',
      [
        Validators.required,
        Validators.email
      ]
    ],
    password: ['',
      [
        Validators.required,
        Validators.min(8),
        Validators.pattern(this.passwordRegx)
      ]
    ]
  });

  //Version * 
  /// pourquoi this.sessionService.sessionInformation is undefined ??

  // Version * 
  // public ngOnInit(): void {
  //   //TODO remove 
  //   console.log(this.user);
  //   this.userService
  //     .getById(this.sessionService.sessionInformation!.id.toString())
  //     .subscribe((user: User) => this.user = user);
  //   //TODO remove 
  //   console.log(this.user);
  // }

  // Version ***
  public ngOnInit(): void {
    this.authService.me().subscribe(
      (user: User) => {
        this.user = user;
        this.initForm(user);
      }
    )
    console.log(this.user);
  }


  private initForm(user: User): void {

    this.form = this.formBuilder.group({
      username: [user ? user.username : '',
      [
        Validators.required,
        Validators.min(6),
        Validators.max(20)
      ]
      ]
      ,
      email: [user ? user.email : '',
      [
        Validators.required,
        Validators.email
      ]
      ],
      password: [user ? user.password : '',
      [
        Validators.required,
        Validators.min(8),
        Validators.pattern(this.passwordRegx)
      ]
      ]
    });
  }

  public submit(): void {

    const formData = new FormData();

    this.formMod = this.form;

    console.log(this.formMod);
    
    formData.append('username', this.formMod!.get('username')?.value);
    formData.append('email', this.formMod!.get('email')?.value);
    formData.append('password', this.formMod!.get('password')?.value);
    console.log(formData);

    this.user = {
      id: this.user!.id,
      username: this.formMod!.get('username')?.value,
      email: this.formMod!.get('email')?.value,
      password: this.formMod!.get('password')?.value   
    };

    // if (!this.user === undefined) {
      console.log("user : ");
      console.log(this.user);
      this.userService
        .update(this.user!.id, formData)
        .subscribe((_: void) => this.exitPage());
    // }
  }

  private exitPage(): void {
    this.router.navigate(['/topics']);
  }

}
