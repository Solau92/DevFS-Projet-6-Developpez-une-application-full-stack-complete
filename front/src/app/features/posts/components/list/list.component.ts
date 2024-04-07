import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { PostsResponse } from '../../interfaces/postsResponse.interface';
import { PostsService } from '../../services/posts.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent {

  public posts$: Observable<PostsResponse> = this.postsService.all();

  constructor(
    private postsService: PostsService
  ) { }

  

}
