import {Component, Input, OnInit} from '@angular/core';
import {NgbModal, NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {Question} from "../model/question/question";
import {Option} from "../model/option/option";
import {QuestionServiceService} from "../service/question-service.service";


@Component({
  selector: 'app-modal-add-question',
  templateUrl: './modal-add-question.component.html',
  styleUrls: ['./modal-add-question.component.css']
})
export class ModalAddQuestionComponent implements OnInit {
  @Input() editQuestion:Question;
  modalForm: NgbActiveModal;
  indexOfTrueOption:number;
  isQuestionChanged:boolean;
  question: Question = {
    id:0,
    content: '',
    options: [],
    answer: ''
  };


  constructor(private modalService: NgbModal, private modal: NgbActiveModal, private questionService: QuestionServiceService) {
    this.modalForm = modal;
  }


  ngOnInit(): void {
    if (this.editQuestion != null) {
      this.isQuestionChanged = true;
      this.question = this.editQuestion;
    }
    else  {
      this.isQuestionChanged = false;
    }
  }

  saveQuestion() {
    let i:number = 0;
    for (let option of this.question.options){
      if (i == this.indexOfTrueOption) {
        this.question.answer = option.content;
      }
      i++;
    }
    this.questionService.sendQuestion(this.question, this.isQuestionChanged).subscribe(result => {
        location.reload();
      },
      error => {
        alert("Error")
      });
    this.modalForm.close("Efrfrf");
  }

  addOptionToQuestion() {
    let opt: Option = new Option();
    this.question.options.push(opt);
  }

  removeOptionToQuestion() {

  }

}
