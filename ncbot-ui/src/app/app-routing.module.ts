import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {QuestionsComponent} from "./questions/questions.component";
import {UsersComponent} from "./users/users.component";
import {MainpageComponent} from "./mainpage/mainpage.component";
import {QuestionComponent} from "./question/question.component";
import {MessageFormComponent} from "./message-form/message-form.component";
import {NotFoundComponent} from "./not-found/not-found.component";


const routes: Routes = [{
  path: 'questions',
  component: QuestionsComponent
},
  {
    path: 'users',
    component: UsersComponent
  },
  {
    path: '',
    component: MainpageComponent,
    pathMatch: 'full'
  },
  {
    path: 'questions/add',
    component: QuestionComponent
  },
  {
    path: 'sendmessage',
    component: MessageFormComponent
  },
  {
    path: '**',
    component: NotFoundComponent
  }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [QuestionsComponent, UsersComponent, MainpageComponent, QuestionComponent,MessageFormComponent, NotFoundComponent];
