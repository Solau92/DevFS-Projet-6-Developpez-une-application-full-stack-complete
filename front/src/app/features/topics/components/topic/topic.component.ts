import { Component } from "@angular/core";
import { TopicsService } from "../../services/topics.service";
import { TopicsResponse } from "../../interfaces/topicsResponse.interface";
import { Observable } from "rxjs";
import { User } from "src/app/interfaces/user.interface";
import { UserService } from "src/app/services/user.service";
import { SessionService } from "src/app/services/session.service";
import { Router } from "@angular/router";

@Component({
  selector: 'app-topic',
  templateUrl: './topic.component.html',
  styleUrls: ['./topic.component.css']
})
export class TopicComponent {

  public topics$: Observable<TopicsResponse> = this.topicsService.all();

  // TODOREMOVE : 
  // public user: User | undefined;
  
  constructor(
    private topicsService: TopicsService,
    private router: Router
    // TODOREMOVE (2 lines): 
    // , private userService: UserService,
    // private sessionService: SessionService
  ) { }

// TODOREMOVE : all method
  // public ngOnInit(): void {
  //   this.userService
  //     .getById(this.sessionService.sessionInformation!.id.toString())
  //     .subscribe((user: User) => this.user = user);
  // }

  // TODO : finish : revoir url + possibilité plusieurs fois souscription...
  public subscribeToTopic(topicId: number): void {

    this.topicsService.createSubscription(topicId).subscribe({
      next: (_: void) => this.router.navigate(['/topics']),})

  }

}
