import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NewPostRequest } from '../../model/new-post-request';
import { PostsService } from '../../services/posts.service';
import { TopicsService } from 'src/app/features/topics/services/topics.service';

@Component({
  selector: 'app-new',
  templateUrl: './new.component.html',
  styleUrls: ['./new.component.css']
})
export class NewComponent {

  public topics$ = this.topicService.getAll();

  constructor(private formBuilder: FormBuilder,
    private postService: PostsService,
    private router: Router,
    private topicService: TopicsService) {
  }

  public form = this.formBuilder.group({    
    // TODO si le temps : voir pourquoi validators de longueur ne marchent pas...
    topic: ['', [Validators.required]],
    title: ['', [Validators.required, Validators.min(5)]],
    content: ['', [Validators.required, Validators.min(20)]]
  })

  public submit(): void {
    const newPostRequest = this.form.value as NewPostRequest;
    this.postService.create(newPostRequest).subscribe({
      next: (_: void) => this.router.navigate(['/posts']),
    });
  }
  
  public back() {
    window.history.back();
  }

}
