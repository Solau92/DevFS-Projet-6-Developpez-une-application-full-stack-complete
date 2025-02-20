import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { AuthGuard } from "./guards/auth.guard";
import { NotFoundComponent } from "./components/not-found/not-found.component";
import { LandingPageComponent } from "./components/landing-page/landing-page.component";

const routes: Routes = [
    {
        path: '',
        component: LandingPageComponent
    },
    {
        path: 'auth',
        loadChildren: () => import('./features/auth/auth.module').then(m => m.AuthModule)
    },
    {
        path: 'topics',
        canActivate:  [AuthGuard],
        loadChildren: () => import('./features/topics/topics.module').then(m => m.TopicsModule)
    },
    {
        path: 'profile',
        canActivate:  [AuthGuard],
        loadChildren: () => import('./features/profile/profile.module').then(m => m.ProfileModule)
    },
    {
        path: 'posts',
        canActivate:  [AuthGuard],
        loadChildren: () => import('./features/posts/posts.module').then(m => m.PostsModule)
    },
    { path: '404', component: NotFoundComponent },
    { path: '**', redirectTo: '404' },

];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}