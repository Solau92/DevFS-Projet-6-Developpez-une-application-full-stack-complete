import { Component, DoCheck, OnChanges, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/features/auth/services/auth-service';
import { Subscription } from 'src/app/interfaces/subscription.interface';
import { SubscriptionsResponse } from 'src/app/interfaces/subscriptionsResponse.interface';
import { User } from 'src/app/interfaces/user.interface';
import { SessionService } from 'src/app/services/session.service';
import { SubscriptionService } from 'src/app/services/subscription.service';
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

  // TODO : Subscriptions : mais faut l'identifiant de l'utilisateur... 
  // public subscriptions$: Observable<SubscriptionsResponse> = this.subscriptionService.all("2");
  public subscriptions$?: Observable<SubscriptionsResponse>;

  constructor(private formBuilder: FormBuilder,
    private router: Router,
    private userService: UserService,
    private sessionService: SessionService,
    private authService: AuthService,
    private subscriptionService: SubscriptionService
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
    
    //TODO : mettre l'user id correct 
    // this.subscriptions$ = this.subscriptionService.all("2");

    console.log("is logged in mecomponent ?: " + this.sessionService.isLogged);

    this.authService.me().subscribe(
      (user: User) => {
        this.user = user;
        this.initForm(user);
        console.log(this.user.id); //TODO : remove 

        // this.subscriptions$ = this.subscriptionService.all(user.id.toString());
        // console.log(this.subscriptions$); 
      }

    )
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

    //TODO : voir 
    // this.subscriptions$ = this.subscriptionService.all(user.id.toString());
    // console.log(this.subscriptions$);   
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
      password: this.formMod!.get('password')?.value,
      subscriptions: [] // Version SS
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

  // TODO : finish 
  public unSubscribeToTopic(): void {

  }

  public logout(): void {
    this.sessionService.logOut();
    this.router.navigate(['/auth/login'])
  }

}
