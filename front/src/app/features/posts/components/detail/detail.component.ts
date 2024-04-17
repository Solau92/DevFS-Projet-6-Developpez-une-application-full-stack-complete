import { Component, OnInit } from '@angular/core';
import { Post } from '../../model/post.interface';
import { PostsService } from '../../services/posts.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { FormBuilder, Validators } from '@angular/forms';
import { NewCommentRequest } from '../../model/new-comment-request.interface';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {

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
    this.id = this.route.snapshot.paramMap.get('id')!;
    this.post$ = this.postService.getById(this.id);
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
