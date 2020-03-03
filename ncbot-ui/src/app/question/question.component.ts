import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.css']
})
export class QuestionComponent implements OnInit {
  model:AddingQuestionWithOptionsForm = {
    question:'',
    options:[]
  };
  constructor(private httpClient: HttpClient) { }

  ngOnInit(): void {
  }

  sendQuestionWithOptions(): void{
    let url="http://localhost:8080/questions/add";
    this.httpClient.post(url, this.model).subscribe(res =>
      {
        location.reload();
      },
      error => {
        alert("Error");
      }
    )
  }

}

export interface AddingQuestionWithOptionsForm {
  question:string;
  options:Option[];
}

export interface Option {
  content:string;
}
