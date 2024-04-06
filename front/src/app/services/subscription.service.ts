import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SubscriptionsResponse } from '../interfaces/subscriptionsResponse.interface';

@Injectable({
  providedIn: 'root'
})
export class SubscriptionService {

  private pathService = 'api/subscription';

  constructor(private httpClient: HttpClient) { 
    
  }

  public all(id: string): Observable<SubscriptionsResponse> {
    // console.log(this.httpClient.get<SubscriptionsResponse>(this.pathService));
    return this.httpClient.get<SubscriptionsResponse>(`${this.pathService}/${id}`);
  }
}
