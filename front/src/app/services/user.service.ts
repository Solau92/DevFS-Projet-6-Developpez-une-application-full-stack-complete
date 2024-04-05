import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../interfaces/user.interface';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private pathService = 'api/user';

  constructor(private httpClient: HttpClient) { 

  }

  public getById(id: string): Observable<User> {
    return this.httpClient.get<User>(`${this.pathService}/${id}`);
    //TODO modifiy : 
    // return this.httpClient.get<User>("api/user/1");
  }

  //TODO 
  public update(id: number, form: FormData): Observable<void> {
    console.log("id : " + id + " - formData : " + form.get("username"));
    return this.httpClient.put<void>(`${this.pathService}/${id}`, form);
  }
}
