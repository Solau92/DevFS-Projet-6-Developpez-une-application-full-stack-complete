import { Injectable } from '@angular/core';
import { SessionInformation } from '../interfaces/session-information.interface';
import { BehaviorSubject, EMPTY, Observable, catchError, tap } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { User } from '../interfaces/user.interface';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  public isLogged = false;

  // Version ***
  //**// 
  //public user: User | undefined;

  //**// 
  public sessionInformation: SessionInformation | undefined;

  // 
  // public sessionInformation: SessionInformation | undefined;

  private isLoggedSubject = new BehaviorSubject<boolean>(this.isLogged);

  public $isLogged(): Observable<boolean> {
    return this.isLoggedSubject.asObservable();
  }

  // Version *
  // public logIn(user: SessionInformation): void {
  //   this.sessionInformation = user;
  //   //TODO : remove : 
  //   console.log(user);
  //   this.isLogged = true;
  //   this.next();
  // }

  // Version ***
  //**// 
  // public logIn(user: User): void {
  //   this.user = user;
  //   this.isLogged = true;
  //   this.next();
  // }

  //**// 
  public logIn(user: SessionInformation): void {
    this.sessionInformation = user;
    this.isLogged = true;
    this.next();
  }

  //**//
  // public logOut(): void {
  //   localStorage.removeItem('token');
  //   this.user = undefined;
  //   this.isLogged = false;
  //   this.next();
  // }

  //**//
  public logOut(): void {
    this.sessionInformation = undefined;
    this.isLogged = false;
    this.next();
  }

  private next(): void {
    this.isLoggedSubject.next(this.isLogged);
  }

}
