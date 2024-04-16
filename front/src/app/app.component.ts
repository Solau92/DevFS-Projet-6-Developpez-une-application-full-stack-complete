import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { SessionService } from './services/session.service';
import { Observable } from 'rxjs';
import { AuthService } from './features/auth/services/auth-service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  // TODO : nettoyer et voir ce qu'on peut enlever...
  // TODO : voir pourquoi logout ici (et pas login) ?

  public isNotMainPage(): boolean {
    return !(this.router.url === '/');
  }

  public isLogged(): boolean {
    return !(this.router.url.includes('login') || this.router.url.includes('register'));
  }

  constructor(private router: Router,
    private sessionService: SessionService,
    private authService: AuthService) {  
    }

  public $isLogged(): Observable<boolean> {
    return this.sessionService.$isLogged();
  }

  public logout(): void {
    this.sessionService.logOut();
    this.router.navigate([''])
  }

}
