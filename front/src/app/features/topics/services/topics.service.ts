import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { Observable, Subscription } from "rxjs";
import { TopicsResponse } from "../model/topicsResponse.interface";

@Injectable({
  providedIn: 'root'
})
export class TopicsService {

  private pathService = 'api/topic';

  constructor(private httpClient: HttpClient) {
  }

  public getAll(): Observable<TopicsResponse> {
    console.log(this.httpClient.get<TopicsResponse>(this.pathService));
    return this.httpClient.get<TopicsResponse>(this.pathService);
  }

  public createSubscription(topicId: number): Observable<void> {
    return this.httpClient.post<void>(this.pathService + "/subscription", topicId);
  }

  public deleteSubscription(topicId: number): Observable<void> {
    return this.httpClient.delete<void>(this.pathService + "/unsubscription/" + topicId);
  }

}
