import { Component } from "@angular/core";
import { TopicsService } from "../../services/topics.service";
import { TopicsResponse } from "../../interfaces/topicsResponse.interface";
import { Observable } from "rxjs";
import { User } from "src/app/interfaces/user.interface";
import { UserService } from "src/app/services/user.service";
import { SessionService } from "src/app/services/session.service";

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
    private topicsService: TopicsService
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

  // TODO : finish 
  public subscribeToTopic(): void {

    // appeler méthode createSubscription de topics.service.ts
    // Mais il faut récupérer le topic + le user (que je n'ai pas encore)

  }

}
