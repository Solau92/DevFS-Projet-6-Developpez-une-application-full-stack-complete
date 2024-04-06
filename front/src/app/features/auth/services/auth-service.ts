import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { RegisterRequest } from "../interfaces/registerRequest.interface";
import { Observable } from "rxjs";
import { LoginRequest } from "../interfaces/loginRequest.interface";
import { SessionInformation } from "src/app/interfaces/session-information.interface";
import { User } from "src/app/interfaces/user.interface";
import { AuthSuccess } from "../interfaces/authSuccess.interface";

@Injectable({
    providedIn: 'root'
  })
  export class AuthService {
  
    private pathService = 'api/auth';
  
    constructor(private httpClient: HttpClient) { }
  
    public register(registerRequest: RegisterRequest): Observable<void> {
      return this.httpClient.post<void>(this.pathService + "/register", registerRequest);
    }

    // Version *
    // public login(loginRequest: LoginRequest): Observable<SessionInformation> {
    //   return this.httpClient.post<SessionInformation>(this.pathService + "/login", loginRequest);
    // }

    // Version ***
    public login(loginRequest: LoginRequest): Observable<AuthSuccess> {
      return this.httpClient.post<AuthSuccess>(this.pathService + "/login", loginRequest);
    }

    // Version ***
    public me(): Observable<User> {
      return this.httpClient.get<User>(`${this.pathService}/me`);
      // return this.httpClient.get<User>("/api/user/1");
    }
  
  }