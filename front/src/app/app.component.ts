import { Component, inject } from '@angular/core';
import { IsActiveMatchOptions, Router } from '@angular/router';
import { SessionService } from './services/session.service';
import { Observable } from 'rxjs';
import { AuthService } from './features/auth/services/auth-service';
import { User } from './interfaces/user.interface';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  public isNotMainPage(): boolean {
    return !(this.router.url === '/');
  }

  public isLogged(): boolean {
    return !(this.router.url.includes('login') || this.router.url.includes('register'));
  }

  constructor(private router: Router,
    private sessionService: SessionService
    // DONE : ajout secu front
    , private authService: AuthService,
  ) {  }

  //**// Suppression ngOnInit+=

  public $isLogged(): Observable<boolean> {
    return this.sessionService.$isLogged();
  }

  // DONE : ajout secu front 
  public logout(): void {
    this.sessionService.logOut();
    this.router.navigate([''])
  }

  //**//
  // // DONE : ajout secu front 
  // public autoLog(): void {
  //   this.authService.me().subscribe(
  //     (user: User) => {
  //       this.sessionService.logIn(user);
  //     },
  //     (_) => {
  //       this.sessionService.logOut();
  //     }
  //   )
  // }
}
