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

  /**
   * Makes a http request to get all the topics.
   * @returns Observable<TopicsResponse>
   */
  public getAll(): Observable<TopicsResponse> {
    return this.httpClient.get<TopicsResponse>(this.pathService);
  }

  /**
   * Makes a http request to create a new subscription.
   * @param topicId 
   * @returns Observable<void>
   */
  public createSubscription(topicId: number): Observable<void> {
    return this.httpClient.post<void>(this.pathService + "/subscription", topicId);
  }

  /**
   * Makes a http request to delete a subscription.
   * @param topicId 
   * @returns Observable<void>
   */
  public deleteSubscription(topicId: number): Observable<void> {
    return this.httpClient.delete<void>(this.pathService + "/unsubscription/" + topicId);
  }

}
