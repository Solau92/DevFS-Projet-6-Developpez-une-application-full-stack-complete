import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/user.interface';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private pathService = 'api/user';

  constructor(private httpClient: HttpClient) {
  }

  public getById(id: number): Observable<User> {
    return this.httpClient.get<User>(this.pathService + "/" + id);
  }

  public update(id: number, form: FormData): Observable<void> {
    return this.httpClient.put<void>((this.pathService + "/" + id), form);
  }

}
