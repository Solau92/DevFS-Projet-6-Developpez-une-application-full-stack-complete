import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PostsResponse } from '../interfaces/postsResponse.interface';
import { Observable } from 'rxjs';

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
}
