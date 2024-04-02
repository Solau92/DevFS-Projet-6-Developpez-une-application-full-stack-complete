import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { Observable, Subscription } from "rxjs";
import { TopicsResponse } from "../interfaces/topicsResponse.interface";

@Injectable({
  providedIn: 'root'
})
export class TopicsService {

  private pathService = 'api/topic';

  constructor(private httpClient: HttpClient) { }

  public all(): Observable<TopicsResponse> {
    console.log(this.httpClient.get<TopicsResponse>(this.pathService));
    return this.httpClient.get<TopicsResponse>(this.pathService);
  }

  // TODO : finish when User implemented 
  // public createSubscription(subscription: SubscriptionToTopic): Observable<SubscriptionToTopic> {
  //   return this.httpClient.post<SubscriptionToTopic>(this.pathService + "/subscription", subscription)
  // }

}
 