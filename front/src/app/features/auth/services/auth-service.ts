import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { RegisterRequest } from "../model/registerRequest.interface";
import { Observable } from "rxjs";
import { LoginRequest } from "../model/loginRequest.interface";
import { User } from "src/app/model/user.interface";
import { AuthSuccess } from "../model/authSuccess.interface";

@Injectable({
    providedIn: 'root'
  })
  export class AuthService {
  
    private pathService = 'api/auth';
  
    constructor(private httpClient: HttpClient) { }
  
    /**
     * Makes a http request to register the given user.
     * @param registerRequest 
     * @returns Observable<void>
     */
    public register(registerRequest: RegisterRequest): Observable<void> {
      return this.httpClient.post<void>(this.pathService + "/register", registerRequest);
    }

    /**
     * Makes a http request to login a user, given credentials.
     * @param loginRequest 
     * @returns Observable<AuthSuccess>
     */
    public login(loginRequest: LoginRequest): Observable<AuthSuccess> {
      return this.httpClient.post<AuthSuccess>(this.pathService + "/login", loginRequest);
    }

    /**
     * Makes a http request to get the user information.
     * @returns Observable<User>
     */
    public me(): Observable<User> {
      return this.httpClient.get<User>(this.pathService + "/me");
    }
  
  }