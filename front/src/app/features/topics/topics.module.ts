import { CommonModule, registerLocaleData } from "@angular/common";
import localeFr from '@angular/common/locales/fr';
import { LOCALE_ID, NgModule } from "@angular/core";
import { TopicComponent } from "./components/topic/topic.component";
import { TopicsRoutingModule } from "./topics-routing.module";
import { MatButtonModule } from "@angular/material/button";
import { MatCardModule } from "@angular/material/card";
import { MatIconModule } from "@angular/material/icon";
import { MatSnackBarModule } from "@angular/material/snack-bar";
import { MatToolbarModule } from "@angular/material/toolbar";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import {MatGridListModule} from '@angular/material/grid-list'; 
import { FlexLayoutModule } from '@angular/flex-layout';

registerLocaleData(localeFr);

const materialModule = [
  MatButtonModule,
  MatCardModule,
  MatIconModule,
  MatSnackBarModule,
  MatToolbarModule,
  MatFormFieldModule,
  MatInputModule,
  MatGridListModule
]

@NgModule({
  declarations: [
    TopicComponent
  ],
  imports: [
    CommonModule,
    TopicsRoutingModule,
    FlexLayoutModule,
    ...materialModule
  ],
  providers: [
    {
      provide: LOCALE_ID,
      useValue: 'fr-FR'
    },
  ]
})
export class TopicsModule { }