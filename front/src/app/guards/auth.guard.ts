import {Injectable} from "@angular/core";
import {CanActivate, Router} from "@angular/router"; 
import { SessionService } from "../services/session.service";
import { Observable, map } from "rxjs";

@Injectable({providedIn: 'root'})
export class AuthGuard implements CanActivate {

  constructor( 
    private router: Router,
    private sessionService: SessionService,
  ) {
  }

  public canActivate(): boolean {
    console.log("is logged ?: " + this.sessionService.isLogged);

    this.sessionService.$isLogged().subscribe(console.log);

    return true;
    // return this.sessionService.$isLogged().pipe(
      
    //   map((isLogged: boolean) => {
    //   if (isLogged) {
    //     console.log("not logged");
    //     this.router.navigate(['auth/login']);
    //     return false;
    //   }
    //   return true;
    // })
    // );

}
}