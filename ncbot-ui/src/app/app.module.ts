import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppComponent} from './app.component';
import {NavigationComponent} from './navigation/navigation.component';
import {QuestionsComponent} from './questions/questions.component';
import {UsersComponent} from './users/users.component';
import {NotFoundComponent} from './not-found/not-found.component';
import {MainpageComponent} from './mainpage/mainpage.component';
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {QuestionComponent} from './question/question.component';
import {ModalAddQuestionComponent} from './modal-add-question/modal-add-question.component';
import {MessageFormComponent} from './message-form/message-form.component';
import {RouterModule} from "@angular/router";

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    QuestionsComponent,
    UsersComponent,
    NotFoundComponent,
    MainpageComponent,
    QuestionComponent,
    ModalAddQuestionComponent,
    MessageFormComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
