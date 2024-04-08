import {Injectable} from "@angular/core";
import {CanActivate, Router} from "@angular/router"; 
import { SessionService } from "../services/session.service";

@Injectable({providedIn: 'root'})
export class UnauthGuard implements CanActivate {

  constructor( 
    private router: Router,
    private sessionService: SessionService,
  ) {
  }

  public canActivate(): boolean {

    console.log("is logged ?: " + this.sessionService.isLogged);

    this.sessionService.$isLogged().subscribe(console.log);

    if (this.sessionService.isLogged) {
      console.log("logged");
      this.router.navigate(['/topics']);
      return false;
    }
    return true;
  }
}