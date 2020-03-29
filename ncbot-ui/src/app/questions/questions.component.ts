import {Component, OnInit} from '@angular/core';
import {Question} from "../model/question/question";
import {QuestionServiceService} from "../service/question-service.service";
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {ModalAddQuestionComponent} from "../modal-add-question/modal-add-question.component";
import {ImageFileService} from "../service/image-file.service";

@Component({
  selector: 'app-questions',
  templateUrl: './questions.component.html',
  styleUrls: ['./questions.component.css']
})
export class QuestionsComponent implements OnInit {
  questions: Question[];

  constructor(private imageFileService: ImageFileService, private questionService: QuestionServiceService, private modalService: NgbModal) {
  }

  ngOnInit(): void {
    this.questionService.getAllQuestions().subscribe((result: Question[]) => {
        this.questions = result;
      }
    );
  }

  openCreationNewQuestionForm() {
    this.modalService.open(ModalAddQuestionComponent);
  }

  deleteQuestion(id) {
    this.questionService.deleteQuestion(id).subscribe(result => {
      location.reload();
    }, error => {
      alert("Error");
    });
  }

  editQuestion(q: Question) {
    let modalForm = this.modalService.open(ModalAddQuestionComponent);
    modalForm.componentInstance.editQuestion = q;
  }

  processFile(questionId: number, inputImage: any) {
    alert("process file");
    const file: File = inputImage.files[0];
    let currentQuestion = this.questions.filter(x => x.id == questionId)[0];
    alert("EGORKA!");
    currentQuestion.attachment.pending = true;
    currentQuestion.attachment.file = file;
    this.imageFileService.uploadImage(currentQuestion.id, currentQuestion.attachment.file).subscribe(result => {
        alert("Success");
      },
      error => {
        alert("Something go wrong!..")
      });
  }
}
