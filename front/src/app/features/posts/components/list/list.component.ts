import { Component } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { PostsResponse } from '../../model/postsResponse.interface';
import { PostsService } from '../../services/posts.service';
import { BehaviorSubject } from 'rxjs';
import { User } from 'src/app/model/user.interface';
import { AuthService } from 'src/app/features/auth/services/auth-service';
import { Subscription } from 'src/app/model/subscription.interface';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent {

  public posts$: Observable<PostsResponse> = this.postsService.getAll();

  public sortAscen: boolean = true;

  public user: User | undefined;

  public subscriptions: Subscription[] | undefined;

  public ngOnInit(): void {
    this.fetchUserAndPosts();
  }

  constructor(private postsService: PostsService,
    private authService: AuthService
  ) {
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

  private fetchUserAndPosts(): void {

    this.authService.me().subscribe(
      (user: User) => {
        this.user = user;
        this.subscriptions = this.user?.subscriptions;
      });

    let allPosts: PostsResponse;

    this.posts$.subscribe((postResponse: PostsResponse) => {
      allPosts = postResponse;  
      let selectedPosts: PostsResponse = {posts: []};
      for (let post of allPosts.posts) {
        if (this.subscriptions) {
          for (let sub of this.subscriptions) {
            if (post.topic.id === sub.topic.id) {
              selectedPosts.posts.push(post);
            }
          }
        }
      }
      this.posts$ = new BehaviorSubject(selectedPosts);
    });
  }
 
}

