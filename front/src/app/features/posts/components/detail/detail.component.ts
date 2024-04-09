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

    // TODO 
    // récupérer le formulaire 
    const newCommentRequest = this.form.value as NewCommentRequest;

    // rajouter le commentaire au Post$
    // Ou envoyer au back le Post + le nouveau commentaire ? 

    // let post: Post | undefined;
    // this.post$?.subscribe((value) => post = value);

    // this.postService.createComment(newCommentRequest, post).subscribe({
    //   next: (_: void) => this.router.navigate(['/posts']),

    // })

      this.postService.createComment(this.route.snapshot.paramMap.get('id')!, newCommentRequest).subscribe({
      next: (_: void) => this.router.navigate(['/posts']),

    })


    // Plutôt mettre l'id du post ! à récupérer dans l'url 

  }

}
