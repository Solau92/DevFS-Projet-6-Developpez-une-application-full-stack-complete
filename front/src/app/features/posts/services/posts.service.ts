import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PostsResponse } from '../interfaces/postsResponse.interface';
import { Observable } from 'rxjs';
import { NewPostRequest } from '../interfaces/new-post-request';
import { Post } from '../interfaces/post.interface';
import { NewCommentRequest } from '../interfaces/new-comment-request.interface';

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

  public detail(id: string): Observable<Post> {
    return this.httpClient.get<Post>(this.pathService + "/" + id);

  }

  public createComment(postId: string, newCommentRequest: NewCommentRequest) : Observable<void> {
    return this.httpClient.post<void>(this.pathService + "/" + postId + "/comment" , newCommentRequest);

  }
}
