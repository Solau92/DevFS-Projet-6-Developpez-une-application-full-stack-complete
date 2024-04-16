import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NewPostRequest } from '../../interfaces/new-post-request';
import { PostsService } from '../../services/posts.service';
import { AuthService } from 'src/app/features/auth/services/auth-service';
import { TopicsService } from 'src/app/features/topics/services/topics.service';

@Component({
  selector: 'app-new',
  templateUrl: './new.component.html',
  styleUrls: ['./new.component.css']
})
export class NewComponent {

  public topics$ = this.topicService.all();

  constructor(private formBuilder: FormBuilder,
    private postService: PostsService,
    private router: Router,
    private authService: AuthService,
    private topicService: TopicsService) {
  }

  public form = this.formBuilder.group({

    //TODO : validators 
    
    topic: ['', /*, [Validators.required]*/],
    title: [''/*, ,[Validators.required], Validators.min(5)*/],
    content: [''/*, [Validators.required, Validators.min(20)]*/]
  })

  public submit(): void {

    const newPostRequest = this.form.value as NewPostRequest;

    this.postService.create(newPostRequest).subscribe({
      next: (_: void) => this.router.navigate(['/posts']),

    })
  }
  
  public back() {
    window.history.back();
  }

}
