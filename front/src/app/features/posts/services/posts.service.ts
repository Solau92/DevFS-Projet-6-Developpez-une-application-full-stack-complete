import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PostsResponse } from '../model/postsResponse.interface';
import { Observable } from 'rxjs';
import { NewPostRequest } from '../model/new-post-request';
import { Post } from '../model/post.interface';
import { NewCommentRequest } from '../model/new-comment-request.interface';

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

  public detail(id: string): Observable<Post> {
    return this.httpClient.get<Post>(this.pathService + "/" + id);
  }

  public createComment(postId: string, newCommentRequest: NewCommentRequest): Observable<void> {
    return this.httpClient.post<void>(this.pathService + "/" + postId + "/comment", newCommentRequest);
  }

}
