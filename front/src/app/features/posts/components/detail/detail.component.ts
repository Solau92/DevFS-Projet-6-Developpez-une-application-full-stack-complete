import { Component, OnInit } from '@angular/core';
import { Post } from '../../interfaces/post.interface';
import { PostsService } from '../../services/posts.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {

  //TO DO : revoir 
  public post?: Post;

  private id: string | undefined;

  constructor(private postService: PostsService,
    private route: ActivatedRoute,
    private router: Router) {

  }

  ngOnInit(): void {
    //TODO : à revoir 
    this.id = this.route.snapshot.paramMap.get('id')!;
    let postO: Observable<Post> = this.postService.detail(this.id);
    postO.subscribe(post => this.post = post);
  }

}
