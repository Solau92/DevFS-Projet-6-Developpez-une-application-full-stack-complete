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

  /**
   * Makes a http request to get a user given his id.
   * @param id 
   * @returns Observable<User>
   */
  public getById(id: number): Observable<User> {
    return this.httpClient.get<User>(this.pathService + "/" + id);
  }

  /**
   * Makes a http request to update user's information.
   * @param id 
   * @param form 
   * @returns Observable<void>
   */
  public update(id: number, form: FormData): Observable<void> {
    return this.httpClient.put<void>((this.pathService + "/" + id), form);
  }

}
