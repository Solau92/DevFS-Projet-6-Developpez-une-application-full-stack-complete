import { CommonModule, registerLocaleData } from "@angular/common";
import localeFr from '@angular/common/locales/fr';
import { LOCALE_ID, NgModule } from "@angular/core";
import { TopicComponent } from "./components/topic/topic.component";
import { TopicsRoutingModule } from "./topics-routing.module";
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBarModule } from '@angular/material/snack-bar';
registerLocaleData(localeFr);

const materialModule = [
  MatButtonModule,
  MatCardModule,
  MatIconModule,
  MatSnackBarModule,
  MatToolbarModule,
]

@NgModule({
  declarations: [
    TopicComponent
  ],
  imports: [
    CommonModule,
    TopicsRoutingModule,
    ...materialModule
  ],
  // DONE : added
  providers: [
    {
      provide: LOCALE_ID,
      useValue: 'fr-FR'
    },
  ]
})
export class TopicsModule { }