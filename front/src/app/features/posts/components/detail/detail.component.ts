import { Component, OnInit } from '@angular/core';
import { Post } from '../../interfaces/post.interface';
import { PostsService } from '../../services/posts.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { FormBuilder, Validators } from '@angular/forms';
import { NewCommentRequest } from '../../interfaces/new-comment-request.interface';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {

  //TO DO : revoir (voir / TopicComponent) : a priori DONE 
  // public post?: Post;
  public post$?: Observable<Post>;

  private id: string | undefined;

  public form = this.formBuilder.group({
    content: ['', Validators.required]
  })

  constructor(private postService: PostsService,
    private route: ActivatedRoute,
    private router: Router,
    private formBuilder: FormBuilder) {

  }

  ngOnInit(): void {
    //TODO : à revoir : a priori DONE 
    // this.id = this.route.snapshot.paramMap.get('id')!;
    // let postO: Observable<Post> = this.postService.detail(this.id);
    // postO.subscribe(post => this.post = post);
    this.id = this.route.snapshot.paramMap.get('id')!;
    this.post$ = this.postService.detail(this.id);

  }

  public submit(): void {

    const newCommentRequest = this.form.value as NewCommentRequest;

    this.postService.createComment(this.route.snapshot.paramMap.get('id')!, newCommentRequest).subscribe({
      next: (_: void) => this.router.navigate(['/posts']),
    })

  }

  public back() {
    window.history.back();
  }

}
