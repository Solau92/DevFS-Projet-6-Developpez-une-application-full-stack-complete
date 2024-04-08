import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PostsResponse } from '../interfaces/postsResponse.interface';
import { Observable } from 'rxjs';
import { NewPostRequest } from '../interfaces/new-post-request';

@Injectable({
  providedIn: 'root'
})
export class PostsService {

  private pathService = 'api/posts'

  constructor(private httpClient: HttpClient) { 

  }

  public all(): Observable<PostsResponse> {
    console.log(this.httpClient.get<PostsResponse>(this.pathService));
    return this.httpClient.get<PostsResponse>(this.pathService);
  }

  public create(newPostRequest: NewPostRequest): Observable<void> {
    return this.httpClient.post<void>(this.pathService, newPostRequest);
  }

  // public create(form: FormData): Observable<void> {
  //   return this.httpClient.post<void>(this.pathService, FormData);
  // }
}
