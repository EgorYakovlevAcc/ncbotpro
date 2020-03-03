import {Component, OnInit} from '@angular/core';
import {Question} from "../model/question/question";
import {QuestionServiceService} from "../service/question-service.service";
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {ModalAddQuestionComponent} from "../modal-add-question/modal-add-question.component";

@Component({
  selector: 'app-questions',
  templateUrl: './questions.component.html',
  styleUrls: ['./questions.component.css']
})
export class QuestionsComponent implements OnInit {
  questions:Question[];
  constructor(private questionService: QuestionServiceService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.questionService.getAllQuestions().subscribe((result:Question[]) =>
      {
        this.questions = result;
      }
    );
  }

  openCreationNewQuestionForm(){
    this.modalService.open(ModalAddQuestionComponent);
  }

  deleteQuestion(id){
    this.questionService.deleteQuestion(id).subscribe(result => {
      location.reload();
    }, error => {
      alert("Error");
    });
  }

  editQuestion(q:Question){
    let modalForm = this.modalService.open(ModalAddQuestionComponent);
    modalForm.componentInstance.editQuestion = q;
  }
}
