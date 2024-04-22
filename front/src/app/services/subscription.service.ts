import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SubscriptionsResponse } from '../model/subscriptionsResponse.interface';

@Injectable({
  providedIn: 'root'
})
export class SubscriptionService {

  private pathService = 'api/subscription';

  constructor(private httpClient: HttpClient) {
  }

  /**
   * Makes a http request to get all subscriptions.
   * @param id 
   * @returns Observable<SubscriptionsResponse>
   */
  public getAll(id: string): Observable<SubscriptionsResponse> {
    return this.httpClient.get<SubscriptionsResponse>(this.pathService + "/id");
  }
}
