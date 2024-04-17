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

  /**
   * Makes a http request to get all the posts.
   * @returns Observable<PostsResponse>
   */
  public getAll(): Observable<PostsResponse> {
    console.log(this.httpClient.get<PostsResponse>(this.pathService));
    return this.httpClient.get<PostsResponse>(this.pathService);
  }

  /**
   * Makes a http request to create a new post.
   * @param newPostRequest 
   * @returns Observable<void>
   */
  public create(newPostRequest: NewPostRequest): Observable<void> {
    return this.httpClient.post<void>(this.pathService, newPostRequest);
  }

  /**
   * Makes a http request to get a post, given its id.
   * @param id 
   * @returns Observable<Post>
   */
  public getById(id: string): Observable<Post> {
    return this.httpClient.get<Post>(this.pathService + "/" + id);
  }

  /**
   * Makes a http request to create a new comment.
   * @param postId 
   * @param newCommentRequest 
   * @returns Observable<void>
   */
  public createComment(postId: string, newCommentRequest: NewCommentRequest): Observable<void> {
    return this.httpClient.post<void>(this.pathService + "/" + postId + "/comment", newCommentRequest);
  }

}
