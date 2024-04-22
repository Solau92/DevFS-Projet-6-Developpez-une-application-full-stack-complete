import { Component } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { PostsResponse } from '../../model/postsResponse.interface';
import { PostsService } from '../../services/posts.service';
import { BehaviorSubject } from 'rxjs';
import { User } from 'src/app/model/user.interface';
import { AuthService } from 'src/app/features/auth/services/auth-service';
import { Subscription } from 'src/app/model/subscription.interface';
import { Post } from '../../model/post.interface';

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
    console.log(this.posts$);
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

  // public mustBeDisplayed(topicId: number | undefined): boolean {

  //   //topicId : le thème de l'article 
  //   console.log("topic id : " + topicId);

  //   // toutes ls souscriptions 
  //   let subscriptions: Subscription[] | undefined = this.user?.subscriptions;
  //   console.log(subscriptions);

  //   // Si topicId est dans la liste des souscriptions du User : true
  //   if (subscriptions != undefined) {
  //     console.log("if : not undefined");
  //     for (var subscription of subscriptions) {
  //       console.log(subscription.topic.id);
  //       if (subscription.topic.id === topicId) {
  //         console.log("true");
  //         return true;
  //       } else { // à enlever ? 
  //         console.log("false car topicId !=");
  //         return false;
  //       }

  //     }
  //   }
  //   console.log("else : subcriptions undefined");
  //   return false;
  // }

  // private fetchUser(): void {
  //   this.authService.me().subscribe(
  //     (user: User) => {
  //       this.user = user;
  //     })
  // }

  private fetchUserAndPosts(): void {

    this.authService.me().subscribe(
      (user: User) => {
        this.user = user;
        this.subscriptions = this.user?.subscriptions;
      })

    let postTemp: PostsResponse;

    this.posts$.subscribe((postResponse: PostsResponse) => {
      postTemp = postResponse;

      let postTempbis: PostsResponse = {
        posts: []
      };

      for (let post of postTemp.posts) {

        if (this.subscriptions) {
          for (let sub of this.subscriptions) {
            if (post.topic.id === sub.topic.id) {
              postTempbis.posts.push(post);
            }
          }
        }

      }

      //   postTemp.posts.filter(post => {
      //   if (this.subscriptions != undefined) {
      //     for (let sub of this.subscriptions) {
      //       sub.topic.id === post.id;
      //       console.log(sub.topic.id === post.id);
      //       console.log(postTemp.posts);
      //     }
      //   }
      // })

      this.posts$ = new BehaviorSubject(postTempbis);
    })



    //   var postTemp$: PostsResponse = this.posts$.subscribe((postResponse: PostsResponse) => {
    //   postResponse.posts.filter(post => {
    //     if (this.subscriptions != undefined) {
    //       for (let sub of this.subscriptions) {
    //         sub.topic.id === post.id;
    //         console.log(sub.topic.id === post.id);
    //         console.log(postResponse);
    //       }
    //     }
    //   })
    // })

    // this.posts$ = new BehaviorSubject(postTemp$);


  }

  //   let postsTemp$: Observable<PostsResponse> = this.postsService.getAll();

  //   this.subscriptions = this.user?.subscriptions;

  //   postsTemp$.subscribe((postResponse: PostsResponse) => {
  //     postResponse.posts.filter(post => {
  //       if (this.subscriptions != undefined) {
  //         for (let sub of this.subscriptions) {
  //           sub.topic.id === post.id
  //         }
  //       }
  //     })
  //   })

  //   this.posts$ = postsTemp$;
}

