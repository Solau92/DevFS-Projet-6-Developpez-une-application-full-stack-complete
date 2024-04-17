import { Component } from "@angular/core";
import { TopicsService } from "../../services/topics.service";
import { TopicsResponse } from "../../model/topicsResponse.interface";
import { Observable } from "rxjs";
import { User } from "src/app/model/user.interface";
import { UserService } from "src/app/services/user.service";
import { SessionService } from "src/app/services/session.service";
import { Router } from "@angular/router";
import { AuthService } from "src/app/features/auth/services/auth-service";
import { Subscription } from "src/app/model/subscription.interface";

@Component({
  selector: 'app-topic',
  templateUrl: './topic.component.html',
  styleUrls: ['./topic.component.css']
})
export class TopicComponent {

  public topics$: Observable<TopicsResponse> = this.topicsService.getAll();

  public user: User | undefined;

  public ngOnInit(): void {
    this.fetchUser();
  }

  constructor(
    private topicsService: TopicsService,
    private router: Router,
    private authService: AuthService) {
  }

  public subscribeToTopic(topicId: number): void {

    this.topicsService.createSubscription(topicId).subscribe({
      next: (_: void) => {
        this.fetchUser();
      }
    })

  }

  private fetchUser(): void {
    this.authService.me().subscribe(
      (user: User) => {
        this.user = user;
      })
  }

  public isNotAlreadySubscriben(topicId: number): boolean {

    // TODO : réécrire 
    let subscriptions: Subscription[] | undefined = this.user?.subscriptions;

    if (subscriptions != undefined) {
      for (var subscription of subscriptions) {
        if (subscription.topic.id === topicId) {
          return false;
        }
      }
      return true;
    }
    return true;
  }
}
