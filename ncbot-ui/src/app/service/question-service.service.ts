import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Question} from "../model/question/question";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class QuestionServiceService {
  host = "http://localhost:8080/questions/";
  question: Question;

  constructor(private httpClient: HttpClient) {
  }

  getAllQuestions(): Observable<any> {
    let url = this.host + "all/text";
    return this.httpClient.get(url);
  }

  sendQuestion(question: Question, isQuestionChanged: boolean): any {
    let url:string;
    if (isQuestionChanged) {
      url = this.host + "edit";
    } else {
      url = this.host + "add";
    }
    return this.httpClient.post(url, question);
  }

  deleteQuestion(id): Observable<any> {
    let url = this.host + "remove?id=" + id;
    return this.httpClient.get(url);
  }

  editQuestion(id): Observable<any> {
    let url = this.host + "edit?id=" + id;
    return this.httpClient.get(url);
  }
}
