import { Component } from "@angular/core";
import { TopicsService } from "./topics.service";
import { TopicsResponse } from "./topicsResponse.interface";
import { Observable } from "rxjs";

@Component({
  selector: 'app-topic',
  templateUrl: './topic.component.html',
  styleUrls: ['./topic.component.css']
})
export class TopicComponent {

  public topics$: Observable<TopicsResponse> = this.topicsService.all();

  constructor(
    private topicsService: TopicsService
  ) { }

}
