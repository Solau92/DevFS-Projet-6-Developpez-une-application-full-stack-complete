import { Component } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { PostsResponse } from '../../model/postsResponse.interface';
import { PostsService } from '../../services/posts.service';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent {

  public posts$: Observable<PostsResponse> = this.postsService.getAll();

  public sortAscen: boolean = true;

  constructor(private postsService: PostsService) {
  }

  public sortDesc(): void {
    this.posts$.subscribe(
      (postReponse: PostsResponse) => {
        postReponse.posts.sort((a, b) => {
          if (a.createdAt < b.createdAt) return 1;
          if (a.createdAt > b.createdAt) return -1;
          return 0;
        });
        this.posts$ = new BehaviorSubject(postReponse);
        this.sortAscen = false;
      }
    );
  }

  public sortAsc(): void {
    this.posts$.subscribe(
      (postReponse: PostsResponse) => {
        postReponse.posts.sort((a, b) => {
          if (a.createdAt < b.createdAt) return -1;
          if (a.createdAt > b.createdAt) return 1;
          return 0;
        });
        this.posts$ = new BehaviorSubject(postReponse);
        this.sortAscen = true;
      }
    );
  }

}
