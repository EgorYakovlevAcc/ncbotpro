import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {NavigationComponent} from './navigation/navigation.component';
import {RouterModule, Routes} from "@angular/router";
import {QuestionsComponent} from './questions/questions.component';
import {UsersComponent} from './users/users.component';
import {NotFoundComponent} from './not-found/not-found.component';
import {MainpageComponent} from './mainpage/mainpage.component';
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import { QuestionComponent } from './question/question.component';
import { ModalAddQuestionComponent } from './modal-add-question/modal-add-question.component';

const appRoutes: Routes = [
  {
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
    path: '**',
    component: NotFoundComponent
  }
];

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    QuestionsComponent,
    UsersComponent,
    NotFoundComponent,
    MainpageComponent,
    QuestionComponent,
    ModalAddQuestionComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
