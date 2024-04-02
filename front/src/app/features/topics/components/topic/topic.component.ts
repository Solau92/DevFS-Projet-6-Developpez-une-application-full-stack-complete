import { Component } from "@angular/core";
import { TopicsService } from "../../services/topics.service";
import { TopicsResponse } from "../../interfaces/topicsResponse.interface";
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
