import { CommonModule, registerLocaleData } from "@angular/common";
import localeFr from '@angular/common/locales/fr';
import { NgModule } from "@angular/core";
import { TopicComponent } from "./components/topic/topic.component";
import { TopicsRoutingModule } from "./topics-routing.module";

registerLocaleData(localeFr);

const materialModules: never[] = [
];

@NgModule({
  declarations: [
    TopicComponent
  ],
  imports: [
    CommonModule,
    TopicsRoutingModule
  ]
})
export class TopicsModule { }